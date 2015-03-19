/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers;

import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Participant;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
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

/**
 *
 * @author fureta
 */
@WebServlet(name = "MedaillesDUneEpreuveHTML", urlPatterns = {"/MedaillesDUneEpreuveHTML"})
public class MedaillesDUneEpreuveHTML extends HttpServlet {

    String createTabMedailles(ArrayList<Participant> listP, String forme) throws SQLException {
        // metaDonnee du rowSet participants renvoye par la request
        StringBuilder resMedailles = new StringBuilder();

        // tableau si les participants sont des equipe
        if (forme.equals("equipe")) {
            //en tete tr +2 colonnes th idEquipe et Pays
            resMedailles.append("<thead><tr>");
            resMedailles.append("<th>").append("idEquipe").append("</th>");
            resMedailles.append("<th>").append("pays").append("</th>");
            
            resMedailles.append("</tr></thead>");
            resMedailles.append("<tbody>");

            //parcours du rowSet poru remplir le tableau
            int i=0;
            for(i=0; i<listP.size(); i++) {
                Equipe equipe = (Equipe) listP.get(i);
                //ouverture row
                resMedailles.append("<tr>");
                //colonne idEquipe et colonne Pays
                resMedailles.append("<td>").append(equipe.getIdEquipe()).append("</td>");
                resMedailles.append("<td>").append(equipe.getPays()).append("</td>");
                //fermeture row
                resMedailles.append("</tr>");
            }
        }
        else{           
            //en tete tr +4 colonnes th idSportif,prenom, nom et et Pays
            resMedailles.append("<thead><tr>");
            resMedailles.append("<th>").append("idSportif").append("</th>");
            resMedailles.append("<th>").append("prenom").append("</th>");
            resMedailles.append("<th>").append("nom").append("</th>");
            resMedailles.append("<th>").append("pays").append("</th>");

            //parcours du rowSet poru remplir le tableau
            int i=0;
            for(i=0; i<listP.size(); i++) {
                Sportif sportif = (Sportif) listP.get(i);
                //ouverture row
                resMedailles.append("<tr>");
                //colonne idEquipe et colonne Pays
                resMedailles.append("<td>").append(sportif.getIdSportif()).append("</td>");
                resMedailles.append("<td>").append(sportif.getPrenom()).append("</td>");
                resMedailles.append("<td>").append(sportif.getNom()).append("</td>");
                resMedailles.append("<td>").append(sportif.getPays()).append("</td>");
                resMedailles.append("</tr>");
            }    
        }

        resMedailles.append("</tbody>");

        return resMedailles.toString();
    }

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

        String tableauMedailles ="";
        try (PrintWriter out = response.getWriter()) {

            //rowset participants renvoyees par la request
            String forme = (String) request.getAttribute("formeParticipant");
            if(forme.equals("equipe")){
                ArrayList<Participant> listMedaillesEquipe = new ArrayList();
                listMedaillesEquipe= (ArrayList<Participant>) request.getAttribute("listMedailles");
                tableauMedailles = createTabMedailles(listMedaillesEquipe, forme);
                
            }
            else{
                ArrayList<Participant> listMedaillesSportifs = new ArrayList();
                listMedaillesSportifs= (ArrayList<Participant>) request.getAttribute("listMedailles");
                tableauMedailles = createTabMedailles(listMedaillesSportifs, forme);
            }
                     
            out.println("<table class=\"table tabParticipant\">");
            out.println(tableauMedailles);
            out.println("</table>");  
           

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantsDUneEpreuveHTML.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(), ex);
        }
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            out.println("coucou");
////            out.println("<table class=\"tabParticipant\">");
////            out.println(tableauParticipants);
////            out.println("</table>");   
//        }
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
