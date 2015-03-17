/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.Panier;

import CyberComp_G2.Model.Panier.Panier;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gato
 */
@WebServlet(name = "changementQuantitee", urlPatterns = {"/changementQuantitee"})
public class changementQuantitee extends HttpServlet {

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
        /* On Recupère le panier de la session*/
        HttpSession session = request.getSession();
        Panier sessionPanier = (Panier) session.getAttribute("sessionPanier");
        /* On Recupère les parametres de la session qui vont induire une modification
         panier*/
        String paramNumBillet = request.getParameter("numeroDuBillet");
        String paramQuantitee = request.getParameter("quantitee");

        String[] infoBillets = paramNumBillet.split(":");
        int numeroDuBilletAChanger = Integer.parseInt(infoBillets[1]);
        int quantitee = Integer.parseInt(paramQuantitee);
        
        /* Modification du nombre de billet dans le panier  */
        if(quantitee!=0){
            sessionPanier.modifierNombreDeBillet(numeroDuBilletAChanger, quantitee);
        }
        request.getRequestDispatcher("WEB-INF/panier.jsp").forward(request, response);
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
