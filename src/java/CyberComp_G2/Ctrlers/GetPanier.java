/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

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
@WebServlet(name = "GetPanier", urlPatterns = {"/GetPanier"})
public class GetPanier extends HttpServlet {

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
        
        
        /* Lors de l'ajout d'une place, on récupère dans un premier temps le panier */
        HttpSession session = request.getSession(true);
        Panier sessionPanier = (Panier)session.getAttribute("sessionPanier");
        
        /* On cherche à savoir si la personne a selectionné un 'Billet' ou un 'TicketVideo' */
        String typeDeBillets = request.getParameter("epreuvesRadio");
        /* String[0] contient le type : Billet ou TicketVideo et String[1] contient l'ID de l'epreuve  */
        String[] infoBillets = typeDeBillets.split(":");
        int idEpreuve = Integer.parseInt(infoBillets[1]);
        
        /* Selectionne le nombre de places a ajouter au panier*/
        String places = request.getParameter("epreuvesNbPlaces");
        int nombreDePlaces = Integer.parseInt(places);
        
        sessionPanier.
        
        
        
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
