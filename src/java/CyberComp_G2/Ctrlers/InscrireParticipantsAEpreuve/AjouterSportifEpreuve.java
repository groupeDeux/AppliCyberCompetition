/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.InscrireParticipantsAEpreuve;

import CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO;
import CyberComp_G2.DAO.InscrireParticipantAEpreuve.ModifierParticipationsDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author magourar
 */
@WebServlet(name = "AjouterSportifEpreuve", urlPatterns = {"/AjouterSportifEpreuve"})
public class AjouterSportifEpreuve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idEpreuve = request.getParameter("idEpreuve");
        String idParticipant = request.getParameter("idSportif");
        ArrayList<Sportif> listSportifsInscrits = new ArrayList();
        ArrayList<Sportif> listSportifsCompatibles = new ArrayList();

        /* Pour test compatibilite hors requete sql?
         String categorieEpreuve;
         String nbPersonneFixe;
         categorie*/
        try {
            /* ----- Mise a jour la liste des sportif inscrits ----- */

            /* --- Ajout d'un n-uplet avec les parametres (idEpreuve,idParticipant)*/
            ModifierParticipationsDAO.ajouterSportifUnique(Integer.parseInt(idEpreuve), Integer.parseInt(idParticipant));

            /* --- Calcul nouvelle liste des sportifs inscrits */
            CachedRowSet rowSetSportifsInscrits = GetParticipantsDAO.getSportifInscritAEpreuve(Integer.parseInt(idEpreuve));

            /* cree un objet sportif  pour chaque ligne du rowset parcouru
             et le met dans l arrayList listSportifsInscrits */
            while (rowSetSportifsInscrits.next()) {
                // recupereation les informations de chaque Sportif
                listSportifsInscrits.add(new Sportif(rowSetSportifsInscrits.getInt("idSportif"), rowSetSportifsInscrits.getString("pays"), rowSetSportifsInscrits.getString("prenom"), rowSetSportifsInscrits.getString("nom"), rowSetSportifsInscrits.getString("dateNaissance"), rowSetSportifsInscrits.getString("genre")));
            }

            /* ----- Calcul nouvelle liste des sportif  compatibles ----- 
             recuperation des donnees BD chargees avec DAO dans un rowSet*/
            CachedRowSet rowSetSportifsCompatibles = GetParticipantsDAO.getlesSportifsCompatiblesEtNonInscritsEpreuveCat(Integer.parseInt(idEpreuve));

            /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listSportifsCompatibles*/
            while (rowSetSportifsCompatibles.next()) {
                // recupereation les informations de sportif 
                listSportifsCompatibles.add(new Sportif(rowSetSportifsCompatibles.getInt("idSportif"), rowSetSportifsCompatibles.getString("pays"), rowSetSportifsCompatibles.getString("prenom"), rowSetSportifsCompatibles.getString("nom"), rowSetSportifsCompatibles.getString("dateNaissance"), rowSetSportifsCompatibles.getString("genre")));
            }
        } catch (SQLException | CategorieException ex) {
            //log(ex.getMessage());
            request.setAttribute("messErreur", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/ErreurInscriptionepreuve.jsp").forward(request, response);
        }
        

        // session pour passer les attributs
        HttpSession session = request.getSession(true);
        /* ajoute la liste en attribut de la reponse */
        session.setAttribute("listSportifsInscrits", listSportifsInscrits);
        session.setAttribute("listSportifsCompatibles", listSportifsCompatibles);
        request.setAttribute("idEpreuveIndSelec", idEpreuve);
        request.setAttribute("activeTab", 2); //onglet actif passe en parametre
        request.getRequestDispatcher("WEB-INF/inscrireParticipantAEpreuve.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
