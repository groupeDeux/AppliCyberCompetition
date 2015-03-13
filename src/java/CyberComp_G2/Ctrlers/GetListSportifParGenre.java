/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import java.io.IOException;
import java.io.PrintWriter;
import static java.rmi.server.LogStream.log;
import java.sql.SQLException;
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
@WebServlet(name = "GetListSportifParGenre", urlPatterns = {"/GetListSportifParGenre"})
public class GetListSportifParGenre extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder rep = new StringBuilder(); // On en a besoin pour construire la page HTML ?  
        String categorie = request.getParameter("categorie"); // le controleur récupere le parametre categorie issu de la page Html   ??

        try (PrintWriter out = response.getWriter()) {

            // recuperation des donnees BD chargees avec DAO dans un rowSet
            CachedRowSet rowSetSportifParGenre = GetParticipantsDAO.getSportifsParGenre(categorie);
            //Parcours du rowSet pour creer la liste deroulante via "<option value=' de tout les  sportifs  classé par categorie feminin /masculin 
            // chaque ligne de la liste déroulante comporte l'idSportif + le nom +le prénom 

            while (rowSetSportifParGenre.next()) {
                String idSportif = rowSetSportifParGenre.getString("idSportif");
                String nom = rowSetSportifParGenre.getString("nom");
                String prenom = rowSetSportifParGenre.getString("prenom");
                //ajouter à la liste déroulante toute les ligne 
                rep.append("<option value='").append(rowSetSportifParGenre.getString("idSportif")).append("'>").append(idSportif).append(" : ").append(nom).append(" ").append(prenom).append("</option>");
            }
            out.println(rep);
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
        
     
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
