/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.Panier;

import CyberComp_G2.DAO.Panier.SetPanierDAO;
import CyberComp_G2.Exceptions.TypeTicketNonCorrect;
import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import CyberComp_G2.Model.Panier.Panier;
import CyberComp_G2.Model.Utilisateur.Utilisateur;
import java.io.IOException;
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

    @Resource(name = "jdbc/BDCyberCompetition")
    private DataSource dataSource;

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
        Panier sessionPanier = (Panier) session.getAttribute("sessionPanier");
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");

        ArrayList<Epreuve> lesEpreuvesAuPanier = sessionPanier.getLesEpreuvesAuPanier();
        ArrayList<Integer> nombreDElement = sessionPanier.getNombreDeBillet();
        ArrayList<String> listeAuPanier = sessionPanier.getListeAuPanier();

        int idTransaction = 0;
        try (Connection conn = dataSource.getConnection()) {
            try {
                conn.setAutoCommit(false);
                SetPanierDAO.addUtilisateur(conn, utilisateur);
                idTransaction = SetPanierDAO.getNewIDTransaction(conn);
                SetPanierDAO.addTransaction(conn, idTransaction, utilisateur.getMail());
                int idTicket = SetPanierDAO.getNewIDTicket(conn);
                int i;
                for (i = 0; i < lesEpreuvesAuPanier.size(); i++) {
                    int nbElement = 1;
                    int idEpreuve = lesEpreuvesAuPanier.get(i).getIdEpreuve();
                    String typeTicket = listeAuPanier.get(i);
                    while (nbElement <= nombreDElement.get(i)) {
                        SetPanierDAO.addTicket(conn, idTicket, idTransaction, idEpreuve);
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
                        nbElement++;
                    }
                }

                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException | TypeTicketNonCorrect ex) {
            request.setAttribute("messageErreur", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/ErreurPanier.jsp").forward(request, response);
        }

        /* Fin de la session, et du panier !  */
        session.invalidate();
        request.setAttribute("idTransaction", idTransaction);
        request.getRequestDispatcher("WEB-INF/commandeTermine.jsp").forward(request, response);

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
