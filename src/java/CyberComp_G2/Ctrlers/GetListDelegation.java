/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.Model.ConstituerEquipe.Delegation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "getListDelegation", urlPatterns = {"/getListDelegation"})
public class GetListDelegation extends HttpServlet {

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
        /* rowSetdelegation de type cachedRowSet
              =set resultat issu de requete BD garde en memoire meme sans connexion*/
        CachedRowSet rowSetDelegation;
        ArrayList<Delegation> listDelegations = new ArrayList();
        try{
            // Recuperation rowSet avec appel DAO
             rowSetDelegation= GetConsulterEquipeDAO.getDelegations();
             /* cree un objet Delegation pour chaque ligne du rowset parcouru
                et le met dans l arrayList listDelegation */
             while(rowSetDelegation.next()){
                // recupereation du pays uniquement(premiere colonne du rowset recupere)
                 listDelegations.add(new Delegation(rowSetDelegation.getString(1),rowSetDelegation.getInt(2)));
             }
                       
        }catch (SQLException ex){
            
        }
        
        /* ajoute l'objet listDelegations en attribut de la reponse */
       
        request.setAttribute("listDelegations", listDelegations);
        request.getRequestDispatcher("/temp.jsp").forward(request, response);
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
