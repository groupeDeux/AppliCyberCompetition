/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi
 */
@WebServlet(name = "GetListSportifParDelgation", urlPatterns = {"/GetListSportifParDelgation"})
public class GetListSportifParDelgation extends HttpServlet {

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
         
         try(PrintWriter out = response.getWriter()){
            
            CachedRowSet rowSetSportifParDelegation=GetConsulterEquipeDAO.getSportifsDUneDelegation(delegation);
            while(rowSetSportifParDelegation.next()){
                String nomSportif = rowSetSportifParDelegation.getString("nom");
                String prenomSportif = rowSetSportifParDelegation.getString("prenom");
//               if (nomEquipe.equals("null")) {
//                    nomEquipe="pas de nom";
//               }
                rep.append("<option value='").append(rowSetSportifParDelegation.getString("idSportif")).append("'>").append(rowSetSportifParDelegation.getString("idSportif")).append(" : ").append(nomSportif).append(" ").append(prenomSportif);
            }
            out.println(rep);
        }catch (SQLException ex){
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
