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
import java.sql.ResultSetMetaData;
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
 * @author fureta
 */
@WebServlet(name = "ParticipantsDUneEpreuveHTML", urlPatterns = {"/ParticipantsDUneEpreuveHTML"})
public class ParticipantsDUneEpreuveHTML extends HttpServlet {

    String createTabParticipants(ArrayList<Participant> listP, String forme) throws SQLException {
        // metaDonnee du rowSet participants renvoye par la request
        StringBuilder resParticipants = new StringBuilder();

        // tableau si les participants sont des equipe
        if (forme.equals("equipe")) {
            //en tete tr +2 colonnes th idEquipe et Pays
            resParticipants.append("<thead><tr>");
            resParticipants.append("<th>").append("idEquipe").append("</th>");
            resParticipants.append("<th>").append("pays").append("</th>");
            
            resParticipants.append("</tr></thead>");
            resParticipants.append("<tbody>");

            //parcours du rowSet poru remplir le tableau
            int i=0;
            for(i=0; i<listP.size(); i++) {
                Equipe equipe = (Equipe) listP.get(i);
                //ouverture row
                resParticipants.append("<tr>");
                //colonne idEquipe et colonne Pays
                resParticipants.append("<td>").append(equipe.getIdEquipe()).append("</td>");
                resParticipants.append("<td>").append(equipe.getPays()).append("</td>");
                //fermeture row
                resParticipants.append("</tr>");
            }
        }
        else{
            
            //en tete tr +4 colonnes th idSportif,prenom, nom et et Pays
            resParticipants.append("<thead><tr>");
            resParticipants.append("<th>").append("idSportif").append("</th>");
            resParticipants.append("<th>").append("prenom").append("</th>");
            resParticipants.append("<th>").append("nom").append("</th>");
            resParticipants.append("<th>").append("pays").append("</th>");

            //parcours du rowSet poru remplir le tableau
            while (crs.next()) {
                //ouverture row
                resParticipants.append("<tr>");
                //colonne idEquipe et colonne Pays
                resParticipants.append("<th>").append(crs.getString("idSportif")).append("</th>");
                resParticipants.append("<th>").append(crs.getString("prenom")).append("</th>");
                resParticipants.append("<th>").append(crs.getString("nom")).append("</th>");
                resParticipants.append("<th>").append(crs.getString("pays")).append("</th>");
                //fermeture row
                resParticipants.append("</tr>");
            }    
        }

        resParticipants.append("</tbody>");

        return resParticipants.toString();
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

        String tableauParticipants ="";
        try (PrintWriter out = response.getWriter()) {

            //rowset participants renvoyees par la request
            String forme = (String) request.getAttribute("formeParticipant");
            if(forme.equals("equipe")){
                ArrayList<Participant> listEquipesInscrites = new ArrayList();
                listEquipesInscrites= (ArrayList<Participant>) request.getAttribute("listParticipants");
                tableauParticipants = createTabParticipants(listEquipesInscrites, forme);
                
            }
            else{
                ArrayList<Participant> listSportifsInscrits = new ArrayList();
                listSportifsInscrits= (ArrayList<Participant>) request.getAttribute("listParticipants");
                tableauParticipants = createTabParticipants(listSportifsInscrits, forme);
            }
                        
           

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantsDUneEpreuveHTML.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(), ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("coucou");
//            out.println("<table class=\"tabParticipant\">");
//            out.println(tableauParticipants);
//            out.println("</table>");   
        }
    }
    
     /*String forme = (String) request.getAttribute("formeParticipant");
             if (forme.equals("equipe")) {
             ArrayList<Equipe> lesParticipants = (ArrayList<Equipe>) request.getAttribute("listParticipants");
             if (lesParticipants != null) {
             if (lesParticipants.size() != 0) {
             for (i = 0; i < lesParticipants.size(); i++) {
             int idEquipe = lesParticipants.get(i).getIdEquipe();
             String pays = lesParticipants.get(i).getPays();

             <div>  < %= idEquipe % > -< %= pays % > < / div > 
                                                            
             }
             } else {
             <div> Aucune équipe ne peut être inscrite  </div >
             }
             }
             } else {
             ArrayList<Sportif> lesParticipants = (ArrayList<Sportif>) request.getAttribute("listParticipants");
             if (lesParticipants != null) {
             if (lesParticipants.size() != 0) {
             for (i = 0; i < lesParticipants.size(); i++) {
             int idSportif = lesParticipants.get(i).getIdSportif();
             String nom = lesParticipants.get(i).getNom();
             String prenom = lesParticipants.get(i).getPrenom();
             String pays = lesParticipants.get(i).getPays();

             // <div> <%= idSportif%> : <%=prenom%> <%=nom%> <%=pays%> </div> <%
             }
             } else {
             <div> Aucune équipe ne peut être inscrite  </div >
             }
             }
             }
             */
            /* TODO output your page here. You may use following sample code. */

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
