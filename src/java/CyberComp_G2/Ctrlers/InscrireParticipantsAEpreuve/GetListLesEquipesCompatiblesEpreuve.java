/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers.InscrireParticipantsAEpreuve;

import CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author magourar
 */
@WebServlet(name = "GetListLesEquipesCompatiblesEpreuve", urlPatterns = {"/GetListLesEquipesCompatiblesEpreuve"})
public class GetListLesEquipesCompatiblesEpreuve extends HttpServlet {

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
        
        ArrayList<Equipe> listEquipesCompatibles = new ArrayList();
        String idEpreuve = request.getParameter("idEpreuve");

        try {

            // recuperation des donnees BD chargees avec DAO dans un rowSet
            CachedRowSet rowSetEquipesCompatibles = GetParticipantsDAO.getLesEquipesCompatiblesEpreuve(Integer.parseInt(idEpreuve));
           
             /* cree un objet Equipe pour chaque ligne du rowset parcouru
             et le met dans l arrayList listEquipesInscrites */
            while (rowSetEquipesCompatibles.next()) {
                // recupereation les informations de  l'quipe
                listEquipesCompatibles.add(new Equipe(rowSetEquipesCompatibles.getInt("idEquipe"), rowSetEquipesCompatibles.getString("pays"), rowSetEquipesCompatibles.getString("nomEquipe"),rowSetEquipesCompatibles.getString("categorie"),rowSetEquipesCompatibles.getInt("nbMembre")));
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
        } catch (CategorieException ex) {
            Logger.getLogger(GetListLesEquipesCompatiblesEpreuve.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* ajoute la liste en attribut de la reponse */
        request.setAttribute("listEquipesCompatibles", listEquipesCompatibles);
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
