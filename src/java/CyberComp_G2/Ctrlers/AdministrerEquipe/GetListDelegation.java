/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Ctrlers.AdministrerEquipe;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Delegation;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

/**
 *Creer la liste des delagation
 * Apel la page admin.jsp
 * @author fureta
 */
@WebServlet(name = "GetListDelegation", urlPatterns = {"/GetListDelegation"})
public class GetListDelegation extends HttpServlet {

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
        /* rowSetdelegation de type cachedRowSet
              =set resultat issu de requete BD garde en memoire meme sans connexion*/
        
        CachedRowSet rowSetDelegation;
        ArrayList<Delegation> listDelegations = new ArrayList();
        HttpSession session = request.getSession(true);
        String delegation = (String) request.getParameter("delegation");
        String nomEquipe= (String) request.getParameter("nomEquipe");
        String categorie = (String) request.getParameter("categorie");
        Equipe newEquipe = null;
        ArrayList<Sportif> lesSportifs = new ArrayList();
    
        try{
            // Recuperation rowSet avec appel DAO
             rowSetDelegation= GetConsulterEquipeDAO.getDelegations();
             /* cree un objet Delegation pour chaque ligne du rowset parcouru
                et le met dans l arrayList listDelegation */
             while(rowSetDelegation.next()){
                // recupereation du pays (premiere colonne ) et du nombre de sportif (2ieme colonne)
                 listDelegations.add(new Delegation(rowSetDelegation.getString(1),rowSetDelegation.getInt(2)));
    
             }
                       
        }catch (SQLException ex){
         log(ex.getMessage());   
        }
        
        
        
        if(delegation!=null && categorie!=null){
            try{
                if("".equals(nomEquipe)){
                    newEquipe = new Equipe(0, delegation, categorie,2);
                }else{
                    newEquipe = new Equipe(0,delegation,nomEquipe, categorie,2); 
                }
            }catch(CategorieException ex){
               log(ex.getMessage());
            }
             try{
            
            CachedRowSet rowSetSportifParDelegation=GetConsulterEquipeDAO.getSportifsDUneDelegation(delegation);
            while(rowSetSportifParDelegation.next()){
                String nomSportif = rowSetSportifParDelegation.getString("nom");
                String prenomSportif = rowSetSportifParDelegation.getString("prenom");
                int idSportif = rowSetSportifParDelegation.getInt("idsportif");
                String genre = rowSetSportifParDelegation.getString("genre");
                String dateNaissance = rowSetSportifParDelegation.getString("dateNaissance");
                lesSportifs.add(new Sportif(idSportif, delegation,prenomSportif, nomSportif, dateNaissance,genre));
            }

            }catch (SQLException|CategorieException ex){
                log(ex.getMessage());
            }
            session.setAttribute("lesSportifs", lesSportifs); 
        }
        
       
        /* ajoute l'objet listDelegations en attribut de la reponse */
        
        session.setAttribute("newEquipe", newEquipe);
        session.setAttribute("listDelegations", listDelegations);
        request.getRequestDispatcher("/WEB-INF/AdministrerEquipe.jsp").forward(request, response);
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
