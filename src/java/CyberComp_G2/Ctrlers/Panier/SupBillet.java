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
@WebServlet(name = "SupBillet", urlPatterns = {"/SupBillet"})
public class SupBillet extends HttpServlet {

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

        /*  Cette servlet est utile pour le Panier, dans le cas ou on click 
         sur une croix, on va supprimer l'une des listes du panier.
         Si on click sur Vider le panier alors on va supprimer tout le panier 
         */        
        HttpSession session = request.getSession();
        Panier sessionPanier = (Panier) session.getAttribute("sessionPanier");
        String aSupprimer = request.getParameter("numeroDuBillet");
        
        if (aSupprimer.equals("TOUT")) {
            /* On vide le panier */            
            sessionPanier.supprimerLePanierComplet();
            sessionPanier = new Panier();
            request.getRequestDispatcher("WEB-INF/panier.jsp").forward(request, response);
        } else {
            /* On supprime l'element numeroDuBilletASup */
            String[] infoBillets = aSupprimer.split(":");
            int numeroDuBilletASupp = Integer.parseInt(infoBillets[1]);
            sessionPanier.supprimerUnBillet(numeroDuBilletASupp);
            request.getRequestDispatcher("WEB-INF/panier.jsp").forward(request, response);
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
