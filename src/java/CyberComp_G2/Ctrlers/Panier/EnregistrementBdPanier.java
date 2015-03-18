/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers.Panier;

import CyberComp_G2.DAO.Panier.SetPanierDAO;
import CyberComp_G2.Exceptions.TypeTicketNonCorrect;
import CyberComp_G2.Model.Utilisateur.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author danjouv
 */
@WebServlet(name = "EnregistrementBdPanier", urlPatterns = {"/EnregistrementBdPanier"})
public class EnregistrementBdPanier extends HttpServlet {
    
    
     @Resource (name="jdbc/BDCyberCompetition")
    private  DataSource dataSource;
     
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
       
         HttpSession session = request.getSession(true);
         ArrayList<Integer> lesEpreuvesAuPanier =(ArrayList<Integer>)session.getAttribute("lesEpreuvesAuPanier");
         ArrayList<Integer> nombreDElement =(ArrayList<Integer>)session.getAttribute("nombreDElement");
         ArrayList<String> listeAuPanier = (ArrayList<String>)session.getAttribute("listeAuPanier");
         Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
         try(Connection conn =dataSource.getConnection()){
            try{
                conn.setAutoCommit(false);
                SetPanierDAO.addUtilisateur(conn, utilisateur);
                int idTansaction = SetPanierDAO.getNewIDTransaction(conn);
                SetPanierDAO.addTransaction(conn, idTansaction, utilisateur.getNom()+utilisateur.getPrenom());
                int idTicket = SetPanierDAO.getNewIDTicket(conn);
                int i;
                for(i=0;i<lesEpreuvesAuPanier.size();i++){
                    int nbElement =1;
                    int idEpreuve = lesEpreuvesAuPanier.get(i);
                    String typeTicket = listeAuPanier.get(i);
                    while(nbElement<=nombreDElement.get(i)){
                        SetPanierDAO.addTicket(conn, idTicket, idTansaction, idEpreuve);
                        switch (typeTicket) {
                            case "Billet":
                                SetPanierDAO.addBillet(conn, idTicket);
                                break;
                            case "TicketVideo":
                                SetPanierDAO.addTicketVideo(conn, idTicket);
                                break;
                            default:
                                throw new TypeTicketNonCorrect();
                        }
                        idTicket++;
                        i++;
                    }
                }
                SetPanierDAO.addTicket(conn, idTicket, idTansaction, idTicket);
                conn.commit();
                conn.setAutoCommit(true);
            }catch(SQLException ex){
                conn.rollback();
                throw ex;
            }
         }catch(SQLException|TypeTicketNonCorrect ex){
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
