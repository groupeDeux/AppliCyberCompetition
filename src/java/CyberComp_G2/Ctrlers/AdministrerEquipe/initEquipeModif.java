/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers.AdministrerEquipe;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
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
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author danjouv
 */
@WebServlet(name = "initEquipeModif", urlPatterns = {"/initEquipeModif"})
public class initEquipeModif extends HttpServlet {

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
            throws ServletException, IOException{
        
        int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
        Equipe modifEquipe = null;
        try {
            CachedRowSet rowSetlistSprotifs;
            rowSetlistSprotifs = GetConsulterEquipeDAO.getSportifsDUneEquipe(idEquipe);
            
            boolean premmier = true;
            while(rowSetlistSprotifs.next()){
                if (premmier==true){
                    premmier = false;
                    modifEquipe= new Equipe(idEquipe,rowSetlistSprotifs.getString("pays"), rowSetlistSprotifs.getString("nomEquipe"),rowSetlistSprotifs.getString("categorie"), rowSetlistSprotifs.getInt("nbMembre"));
                }
                modifEquipe.addMembre(new Sportif(rowSetlistSprotifs.getInt("idSportif"), rowSetlistSprotifs.getString("prenom"), rowSetlistSprotifs.getString("nom"), rowSetlistSprotifs.getString("genre")));
            }
        }catch(SQLException|CategorieException ex){  
           log(ex.getMessage());
        }
        HttpSession session = request.getSession(true);
        ArrayList<Sportif> lesSportifs = new ArrayList();
        String delegation =  modifEquipe.getPays();
        String categorie = modifEquipe.getCategorie();
        try{
                 CachedRowSet rowSetSportifParDelegation;
                 
                if ("mixte".equals(categorie)){
                    rowSetSportifParDelegation= GetConsulterEquipeDAO.getSportifsDUneDelegation(delegation);
                } else{
                    rowSetSportifParDelegation= GetConsulterEquipeDAO.getSportifsSelonGenre(delegation,categorie);
                }
            
            while(rowSetSportifParDelegation.next()){
                String nomSportif = rowSetSportifParDelegation.getString("nom");
                String prenomSportif = rowSetSportifParDelegation.getString("prenom");
                int idSportif = rowSetSportifParDelegation.getInt("idsportif");
                String genre = rowSetSportifParDelegation.getString("genre");
                String dateNaissance = rowSetSportifParDelegation.getString("dateNaissance");
                lesSportifs.add(new Sportif(idSportif, delegation,prenomSportif, nomSportif, dateNaissance,genre));
            }

           
            }catch (SQLException|CategorieException ex){
            log(ex.getMessage());
            }
        
        session.setAttribute("lesSportifsModif", lesSportifs); 
        session.setAttribute("modifEquipe", modifEquipe);
        session.setAttribute("tabs", 2);
        request.getRequestDispatcher("/WEB-INF/AdministrerEquipe.jsp").forward(request, response);
        
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
