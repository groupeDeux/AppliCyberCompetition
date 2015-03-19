/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.InscrireParticipantsAEpreuve;

import CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import CyberComp_G2.Model.ConsulterEpreuve.EpreuveParEquipe;
import java.io.IOException;
import java.io.PrintWriter;
import static java.rmi.server.LogStream.log;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author agathe
 */
@WebServlet(name = "GetListEquipesInscritesEtCompatibles", urlPatterns = {"/GetListEquipesInscritesEtCompatibles"})
public class GetListEquipesInscritesEtCompatibles extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws CyberComp_G2.Exceptions.CategorieException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idEpreuve = request.getParameter("idEpreuve");
        ArrayList<Equipe> listEquipesInscrites = new ArrayList();
        ArrayList<Equipe> listEquipesCompatibles = new ArrayList();
        

        try {
             /* ----- Les equipes inscrites ----- */
            // recuperation des donnees BD chargees avec DAO dans un rowSet
            CachedRowSet rowSetEquipesInscrites = GetParticipantsDAO.getEquipesInscrites(Integer.parseInt(idEpreuve));
           
             /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listEquipesInscrites */
            while (rowSetEquipesInscrites.next()) {
                // recupereation les informations de  l'quipe
                listEquipesInscrites.add(new Equipe(rowSetEquipesInscrites.getInt("idEquipe"), rowSetEquipesInscrites.getString("pays"), rowSetEquipesInscrites.getString("nomEquipe"),rowSetEquipesInscrites.getString("categorie"),rowSetEquipesInscrites.getInt("nbMembre")));
            }
                     
            /* ----- Les equipes compatibles ----- */
         // recuperation des donnees BD chargees avec DAO dans un rowSet
            CachedRowSet rowSetEquipesCompatibles = GetParticipantsDAO.getLesEquipesCompatiblesEtNonInscrites(Integer.parseInt(idEpreuve));
           
             /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listEquipesCompatibles */
            while (rowSetEquipesCompatibles.next()) {
                // recupereation les informations de  l'quipe
                listEquipesCompatibles.add(new Equipe(rowSetEquipesCompatibles.getInt("idEquipe"), rowSetEquipesCompatibles.getString("pays"), rowSetEquipesCompatibles.getString("nomEquipe"),rowSetEquipesCompatibles.getString("categorie"),rowSetEquipesCompatibles.getInt("nbMembre")));
            }
        } catch (SQLException|CategorieException ex) {
                request.setAttribute("messErreur", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/ErreurInscriptionEpreuve.jsp").forward(request, response);  
           }
        
        
        // session pour passer les attributs
        HttpSession session = request.getSession(true);
        /* ajoute la liste en attribut de la reponse */
        session.setAttribute("listEquipesInscrites", listEquipesInscrites);
        session.setAttribute("listEquipesCompatibles", listEquipesCompatibles);
        request.setAttribute("idEpreuveSelec",idEpreuve);
        request.setAttribute("activeTab", 1); //onglet actif passe en parametre
        request.getRequestDispatcher("WEB-INF/inscrireParticipantAEpreuve.jsp").forward(request, response);
        //request.getRequestDispatcher("WEB-INF/equipesInscrites.jsp").forward(request, response);
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
