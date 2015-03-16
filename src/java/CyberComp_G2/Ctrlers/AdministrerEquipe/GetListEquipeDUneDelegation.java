/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.AdministrerEquipe;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *Renvoie le contenu Html d'un select contenant
 * La list des  equipes de la delegation fournie.
 * @author Gato
 */


@WebServlet(name = "GetListEquipeDUneDelegation", urlPatterns = {"/GetListEquipeDUneDelegation"})
public class GetListEquipeDUneDelegation extends HttpServlet {

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
         StringBuilder rep = new StringBuilder();
         String delegation = request.getParameter("delegation");
         
         ArrayList<Equipe> lesEquipes = new ArrayList();
          try{
          
          CachedRowSet rowSetEquipeParDelegation = new GetConsulterEquipeDAO().getEquipesDUneDelegation(delegation);
          while(rowSetEquipeParDelegation.next()){
                String nomEquipe = rowSetEquipeParDelegation.getString("nomEquipe");
             lesEquipes.add(new Equipe(rowSetEquipeParDelegation.getInt("idEquipe"), delegation, rowSetEquipeParDelegation.getString("categorie"),rowSetEquipeParDelegation.getInt("nbMembre")));
          }
          }catch(SQLException| CategorieException ex){
            log(ex.getMessage());       
           }
          HttpSession session = request.getSession(true);
          session.setAttribute("lesEquipes", lesEquipes);
        session.setAttribute("tabs", 2);
        request.setAttribute("delegation", delegation);
        session.setAttribute("modifEquipe", null);
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
