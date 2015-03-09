/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.DAO.ConsulterEpreuve.GetConsulterEpreuveDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import CyberComp_G2.Model.ConstituerEquipe.Delegation;
import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import CyberComp_G2.Model.ConsulterEpreuve.EpreuveIndividuelle;
import CyberComp_G2.Model.ConsulterEpreuve.EpreuveParEquipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
@WebServlet(name = "GetListEpreuve", urlPatterns = {"/GetListEpreuve"})
public class GetListEpreuve extends HttpServlet {

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

        CachedRowSet rowSetEpreuveEquipe;
        CachedRowSet rowSetEpreuveInv;
        CachedRowSet rowSetDiscipline;
        ArrayList<Epreuve> listEpreuvesEquipe = new ArrayList();
        ArrayList<Epreuve> listEpreuvesInv = new ArrayList();
        ArrayList<String> listDisciplines = new ArrayList();
        String SetDiscipline = request.getParameter("Discipline");
        String SetCategorie = request.getParameter("Categorie");
        String SetnomEpreuve = request.getParameter("nomEpreuve");
        if ("null:null:null".equals(SetDiscipline + ":" + SetCategorie + ":" + SetnomEpreuve)){
            try {
                // Recuperation rowSet avec appel DAO
                rowSetEpreuveEquipe = GetConsulterEpreuveDAO.getEpreuvesEquipe();
                rowSetEpreuveInv = GetConsulterEpreuveDAO.getEpreuvesInv();
                rowSetDiscipline = GetConsulterEpreuveDAO.getDisciplines();
                /* cree un objet Delegation pour chaque ligne du rowset parcouru
                 et le met dans l arrayList listDelegation */
                while (rowSetEpreuveEquipe.next()) {
                    // recupereation du pays uniquement(premiere colonne du rowset recupere)
                    listEpreuvesEquipe.add(new EpreuveParEquipe(rowSetEpreuveEquipe.getInt(1), rowSetEpreuveEquipe.getString(2), rowSetEpreuveEquipe.getString(3), rowSetEpreuveEquipe.getString(4), rowSetEpreuveEquipe.getString(5), rowSetEpreuveEquipe.getString(6), rowSetEpreuveEquipe.getDouble(7), rowSetEpreuveEquipe.getInt(8), rowSetEpreuveEquipe.getString(9), rowSetEpreuveEquipe.getInt(10)));
                }
                while (rowSetEpreuveInv.next()) {
                    // recupereation du pays uniquement(premiere colonne du rowset recupere)
                    listEpreuvesInv.add(new EpreuveIndividuelle(rowSetEpreuveEquipe.getInt(1), rowSetEpreuveEquipe.getString(2), rowSetEpreuveEquipe.getString(3), rowSetEpreuveEquipe.getString(4), rowSetEpreuveEquipe.getString(5), rowSetEpreuveEquipe.getString(6), rowSetEpreuveEquipe.getDouble(7), rowSetEpreuveEquipe.getInt(8), rowSetEpreuveEquipe.getString(9), rowSetEpreuveEquipe.getInt(10)));
                }
                while (rowSetDiscipline.next()) {
                    // recupereation du pays uniquement(premiere colonne du rowset recupere)
                    listDisciplines.add(rowSetDiscipline.getString(1));
                }

            } catch (SQLException | CategorieException | nbPlaceAcheterExeception ex) {
                log(ex.getMessage());
                ex.printStackTrace();

            }
        }
        /* ajoute l'objet listDelegations en attribut de la reponse */
        request.setAttribute("listDisciplines", listDisciplines);
        request.setAttribute("listEpreuveEquipe", listEpreuvesEquipe);
        request.setAttribute("listEpreuveInd", listEpreuvesInv);
        request.getRequestDispatcher("WEB-INF/epreuves.jsp").forward(request, response);

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
