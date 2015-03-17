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
 * @author fureta
 */
@WebServlet(name = "SupprimerParticipationEquipe", urlPatterns = {"/SupprimerParticipationEquipe"})
public class SupprimerParticipationEquipe extends HttpServlet {

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
       //HttpSession session = request.getSession(true);  // SESSION NECESSAIRE ??
        String idEpreuve = request.getParameter("idEpreuve");
        String idParticipant = request.getParameter("idEquipe"); 
        ArrayList<Equipe> listEquipesInscrites = new ArrayList();
        ArrayList<Equipe> listEquipesCompatibles = new ArrayList();
        
        
         try {
             /* ----- Mise a jour participations des equipe ----- */ 
             
            /* --- Ajout d'un n-uplet avec les parametres (idEpreuve, idEquipe)*/
            ModifierParticipationsDAO.supprimerParticipantUnique(Integer.parseInt(idEpreuve),Integer.parseInt(idParticipant));
          
            /* --- Calcul nouvelle liste des equipes inscrites */
            CachedRowSet rowSetEquipesInscrites = GetParticipantsDAO.getEquipesInscrites(Integer.parseInt(idEpreuve));
           
             /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listEquipesInscrites */
            while (rowSetEquipesInscrites.next()) {
                // recupereation les informations de  l'quipe
                listEquipesInscrites.add(new Equipe(rowSetEquipesInscrites.getInt("idEquipe"), rowSetEquipesInscrites.getString("pays"), rowSetEquipesInscrites.getString("nomEquipe"),rowSetEquipesInscrites.getString("categorie"),rowSetEquipesInscrites.getInt("nbMembre")));
            }
            /* ---  Calcul nouvelle liste équipes compatibles et non inscrites*/
            CachedRowSet rowSetEquipesCompatibles = GetParticipantsDAO.getLesEquipesCompatiblesEtNonInscrites(Integer.parseInt(idEpreuve));
           
             /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listEquipesCompatibles */
            while (rowSetEquipesCompatibles.next()) {
                // recupereation les informations de  l'quipe
                listEquipesCompatibles.add(new Equipe(rowSetEquipesCompatibles.getInt("idEquipe"), rowSetEquipesCompatibles.getString("pays"), rowSetEquipesCompatibles.getString("nomEquipe"),rowSetEquipesCompatibles.getString("categorie"),rowSetEquipesCompatibles.getInt("nbMembre")));
            }
                    
            } catch (SQLException|CategorieException ex) {
            log(ex.getMessage());
           }
         
         // session pour passer les attributs
        HttpSession session = request.getSession(true);
        /* ajoute la liste en attribut de la reponse */
        session.setAttribute("listEquipesInscrites", listEquipesInscrites);
        session.setAttribute("listEquipesCompatibles", listEquipesCompatibles);
        request.setAttribute("idEpreuveSelec",idEpreuve);
        request.setAttribute("activeTab", 1); //onglet actif passe en parametre
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
