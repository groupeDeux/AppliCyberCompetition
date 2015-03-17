/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.Panier;

import CyberComp_G2.DAO.ConsulterEpreuve.GetConsulterEpreuveDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import CyberComp_G2.Model.Panier.Panier;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Gato
 */
@WebServlet(name = "AjoutPanier", urlPatterns = {"/AjoutPanier"})
public class AjoutPanier extends HttpServlet {

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
        
        /* Mettre un try catch */
        
        /* Lors de l'ajout d'une place, on récupère dans un premier temps le panier */
        HttpSession session = request.getSession();
        Panier sessionPanier = (Panier)session.getAttribute("sessionPanier");
        
        /* On cherche à savoir si la personne a selectionné un 'Billet' ou un 'TicketVideo' */
        String typeDeBillets = request.getParameter("epreuvesRadio");
        /* String[0] contient le type : Billet ou TicketVideo et String[1] contient l'ID de l'epreuve  */
        String[] infoBillets = typeDeBillets.split(":");
        /* Récupération du numero de idEpreuve dans une nouvelle variable int */
        int idEpreuve = Integer.parseInt(infoBillets[1]);
        
        /* Selectionne le nombre de places a ajouter au panier*/
        String places = request.getParameter("epreuvesNbPlaces");
        int nombreDePlaces = Integer.parseInt(places);
        
        Epreuve epreuveSelectionnee=null;
        CachedRowSet rowSetEpreuve;
        String nomEpreuve="rien"; 
        
        try{
            rowSetEpreuve = new GetConsulterEpreuveDAO().getEpreuvesParId(idEpreuve);
            
            while(rowSetEpreuve.next()){
            
            epreuveSelectionnee = new Epreuve(rowSetEpreuve.getInt(1),
                    rowSetEpreuve.getString(3),rowSetEpreuve.getString(2),
                    rowSetEpreuve.getString(4),rowSetEpreuve.getString(5),
                    rowSetEpreuve.getString(6),rowSetEpreuve.getDouble(7),
                    rowSetEpreuve.getInt(8),rowSetEpreuve.getString(9),
                    0);
            nomEpreuve = epreuveSelectionnee.getNomEpreuve();
            }
            /* NullPointerException a verifier ici */
            sessionPanier.ajouterUnBillet(epreuveSelectionnee, infoBillets[0], nombreDePlaces);
            
        }catch(SQLException | CategorieException | nbPlaceAcheterExeception ex){
            log(ex.getMessage());
            ex.printStackTrace();
        }
        request.getRequestDispatcher("GetPanier").forward(request, response);
        
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

    private Object GetConsulterEpreuveDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
