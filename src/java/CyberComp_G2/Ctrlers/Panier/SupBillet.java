/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.Panier;

import CyberComp_G2.Exceptions.PanierException;
import CyberComp_G2.Model.Panier.Panier;
import CyberComp_G2.Model.Utilisateur.Utilisateur;
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
        Utilisateur sessionUtilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");

        try {
            String paramNumBillet = request.getParameter("numeroDuBillet");

            if (paramNumBillet.equals("TOUT")) {
                /* On vide le panier */
                sessionPanier.supprimerLePanierComplet();
                sessionUtilisateur.setPanierValider(false);
                sessionUtilisateur.setInfoValider(false);
            } else {
                /* On supprime l'element numeroDuBilletASup */
                String[] infoBillets = paramNumBillet.split(":");
                int numeroDuBilletASupp = Integer.parseInt(infoBillets[1]);
                sessionPanier.supprimerUnBillet(numeroDuBilletASupp);
                sessionUtilisateur.setPanierValider(false);
                sessionUtilisateur.setInfoValider(false);
            }
        } catch (PanierException ex) {
            request.setAttribute("messageErreur", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/ErreurPanier.jsp").forward(request, response);
        }

        request.setAttribute("valeurTab", 0);
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
