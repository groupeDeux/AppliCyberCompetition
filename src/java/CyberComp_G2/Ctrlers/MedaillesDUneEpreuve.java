/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsulterEpreuve.GetConsulterEpreuveDAO;
import CyberComp_G2.DAO.ConsulterEpreuve.GetMedaillesDAO;
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
 * @author fureta
 */
@WebServlet(name = "MedaillesDUneEpreuve", urlPatterns = {"/MedaillesDUneEpreuve"})
public class MedaillesDUneEpreuve extends HttpServlet {

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

        // test pour savoir si on renvoi des sportifs ou des equipes
        CachedRowSet rowSetEpreuveEquipe;
        boolean testFormeParEquipe = false;
        // test pour savoir si l'epreuve est passee ou non (affichage médailles ou vente billet)
        String testPresenceMedaille = "false";

        String idEpreuve = request.getParameter("idEpreuve");
        ArrayList<Equipe> listEquipesMedaillees = new ArrayList();
        ArrayList<Sportif> listSportifsMedailles = new ArrayList();

        try {

            /*--- Test sur la forme du participant: equipe ou sportif --- */
            rowSetEpreuveEquipe = new GetConsulterEpreuveDAO().getEpreuvesEquipe();

            while (rowSetEpreuveEquipe.next() & testFormeParEquipe != true) {
                testFormeParEquipe = (Integer.parseInt(idEpreuve) == rowSetEpreuveEquipe.getInt("idEpreuve"));
            }


            /* ----- Participants de forme equipe ----- */
            if (testFormeParEquipe == true) {
                CachedRowSet rowSetEquipesMedaillees = GetMedaillesDAO.getListEquipesMedaillees(Integer.parseInt(idEpreuve));

                while (rowSetEquipesMedaillees.next()) {
                    // recupereation les informations de  l'equipe
                    listEquipesMedaillees.add(new Equipe(rowSetEquipesMedaillees.getInt("idEquipe"), rowSetEquipesMedaillees.getString("pays"), rowSetEquipesMedaillees.getString("nomEquipe"), rowSetEquipesMedaillees.getString("categorie"), rowSetEquipesMedaillees.getInt("nbMembre")));
                }
                 /*--- Test sur presence de medailles --- */
                if (rowSetEquipesMedaillees != null) {
                    testPresenceMedaille="true";
                }
                

            } /* ----- Participants de forme sportif ----- */ 
            else {
                CachedRowSet rowSetSportifsMedailles = GetParticipantsDAO.getSportifInscritAEpreuve(Integer.parseInt(idEpreuve));

                while (rowSetSportifsMedailles.next()) {
                    // recupereation les informations de chaque Sportif
                    listSportifsMedailles.add(new Sportif(rowSetSportifsMedailles.getInt("idSportif"), rowSetSportifsMedailles.getString("pays"), rowSetSportifsMedailles.getString("prenom"), rowSetSportifsMedailles.getString("nom"), rowSetSportifsMedailles.getString("dateNaissance"), rowSetSportifsMedailles.getString("genre")));
                }

                /*--- Test sur presence de medailles --- */
                if (rowSetSportifsMedailles != null) {
                    testPresenceMedaille="true";
                }
            }

        } catch (SQLException | CategorieException ex) {
            //log(ex.getMessage());
            request.setAttribute("messErreur", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/ErreurInscriptionEpreuve.jsp").forward(request, response);
        }

        if (testFormeParEquipe == true) { 
            request.setAttribute("listMedailles", listEquipesMedaillees);  
            request.setAttribute("formeParticipant","equipe");
            request.setAttribute("testPresenceMedaille",testPresenceMedaille);
        }
        else{
            request.setAttribute("listMedailles", listSportifsMedailles);
            request.setAttribute("formeParticipant","sportif");
            request.setAttribute("testPresenceMedaille",testPresenceMedaille);
        }
        request.getRequestDispatcher("WEB-INF/epreuves.jsp").forward(request, response);
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
