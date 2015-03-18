/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.Panier;

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
@WebServlet(name = "ValiderInformations", urlPatterns = {"/ValiderInformations"})
public class ValiderInformations extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        Utilisateur sessionUtilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
        
        /* On récupère maintenant tous les parametres OBLIGATOIRES envoyé par la page informations */
        String num = (String) request.getParameter("numRue");
        int numRue = Integer.parseInt(num);
                
        sessionUtilisateur.setNom((String) request.getParameter("nom"));
        sessionUtilisateur.setPrenom((String) request.getParameter("prenom"));
        sessionUtilisateur.setRue((String) request.getParameter("rue"));
        sessionUtilisateur.setNumRue(Integer.parseInt((String) request.getParameter("numRue")));
        sessionUtilisateur.setVille((String) request.getParameter("ville"));
        sessionUtilisateur.setMail((String) request.getParameter("mail"));
        sessionUtilisateur.setNumTelephone((String) request.getParameter("numTelephone"));
        sessionUtilisateur.setTypeCarte((String) request.getParameter("carte"));
        
        
        /* On valide l'acces à la page suivante */
        
        sessionUtilisateur.setInfoValider(true);
        
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
