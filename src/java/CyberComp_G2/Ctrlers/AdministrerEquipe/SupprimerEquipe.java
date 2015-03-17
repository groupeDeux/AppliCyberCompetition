/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers.AdministrerEquipe;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.DAO.ConsituerEquipe.ModifierEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.GenreMenbreEquipeException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author danjouv
 */
@WebServlet(name = "SupprimerEquipe", urlPatterns = {"/SupprimerEquipe"})
public class SupprimerEquipe extends HttpServlet {

    @Resource (name="jdbc/BDCyberCompetition")
    private  DataSource dataSource;
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
        
        HttpSession session = request.getSession(true);
        ArrayList<Equipe> lesEquipesSup =   (ArrayList<Equipe>)session.getAttribute("lesEquipesSup");
        Equipe equipe = null;
        int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
        int i;
        for(i=0;i<lesEquipesSup.size();i++){
            if(lesEquipesSup.get(i).getIdEquipe() == idEquipe){
                equipe = lesEquipesSup.get(i);
            }
        }
         
        try {
            CachedRowSet lesSportifDeLequipe = GetConsulterEquipeDAO.getSportifsDUneEquipe(idEquipe);
            while(lesSportifDeLequipe.next()){
                equipe.addMembre(new Sportif(lesSportifDeLequipe.getInt("idSportif"), lesSportifDeLequipe.getString("prenom"), lesSportifDeLequipe.getString("nom"), lesSportifDeLequipe.getString("genre")));
            }
            
            ModifierEquipeDAO.SupprimerEquipe(dataSource, equipe);
        } catch (SQLException|CategorieException|GenreMenbreEquipeException ex) {
            Logger.getLogger(SupprimerEquipe.class.getName()).log(Level.SEVERE, null, ex);
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
