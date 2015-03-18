/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers.AdministrerEquipe;

import CyberComp_G2.DAO.ConsituerEquipe.ModifierEquipeDAO;
import CyberComp_G2.Exceptions.GenreMenbreEquipeException;
import CyberComp_G2.Exceptions.nbMenbreEquipeException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author vivi
 */
@WebServlet(name = "ModifierEquipe", urlPatterns = {"/ModifierEquipe"})
public class ModifierEquipe extends HttpServlet {
    
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
        
        HttpSession session = request.getSession(true);
        String estUneCreation = request.getParameter("Creation");
        ArrayList<Sportif> lesSportifs = null ;
       
        if (null != estUneCreation)switch (estUneCreation) {
            case "true":
                Equipe newEquipe = (Equipe) session.getAttribute("newEquipe");
                lesSportifs =  (ArrayList<Sportif>) session.getAttribute("lesSportifs");
                int i,j;
                try{
                for(i=1;i<=newEquipe.getNbDeSportif();i++){
                    for(j=0;j<lesSportifs.size();j++){
                        if(request.getParameter("sportifSelect"+i)==null){
                           throw new nbMenbreEquipeException(i-1); 
                        }
                        if(lesSportifs.get(j).getIdSportif()==Integer.parseInt(request.getParameter("sportifSelect"+i))){
                            newEquipe.addMembre(lesSportifs.get(j));
                        }
                    }
                }
                 if(newEquipe.getNbMembre()<2 || newEquipe.getNbDeSportif()!=newEquipe.getNbMembre()){
                   throw new nbMenbreEquipeException(newEquipe.getNbMembre());
                }
               newEquipe.setIdParticipant(ModifierEquipeDAO.addEquipe(dataSource,newEquipe)); 
               
            }catch(SQLException|GenreMenbreEquipeException|nbMenbreEquipeException ex){
                 request.setAttribute("etat", "erreur");
                request.setAttribute("mesErreur", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/ValidationEquipe.jsp").forward(request, response);
            }   
               request.setAttribute("etat", "creation");
               request.setAttribute("newEquipe", newEquipe);
               request.getRequestDispatcher("/WEB-INF/ValidationEquipe.jsp").forward(request, response);
                break;
            case "false":
                Equipe equipe = (Equipe) session.getAttribute("modifEquipe");
                lesSportifs =  (ArrayList<Sportif>) session.getAttribute("lesSportifsModif");
                 try {
                for(i=1;i<=equipe.getNbDeSportif();i++){
                    for(j=0;j<lesSportifs.size();j++){
                        if(request.getParameter("sportifSelect"+i)==null){
                           throw new nbMenbreEquipeException(i-1); 
                        }
                        if(lesSportifs.get(j).getIdSportif()==Integer.parseInt(request.getParameter("sportifSelect"+i))){
                            equipe.addMembre(lesSportifs.get(j));
                        }
                    }
                }   
                if(equipe.getNbMembre()<2 || equipe.getNbDeSportif()!=equipe.getNbMembre()){
                   throw new nbMenbreEquipeException(equipe.getNbMembre());
                }
                ModifierEquipeDAO.modifEquipe(dataSource,equipe);
                
            } catch (SQLException |GenreMenbreEquipeException|nbMenbreEquipeException ex) {
                request.setAttribute("etat", "erreur");
                request.setAttribute("mesErreur", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/ValidationEquipe.jsp").forward(request, response);
            }
               request.setAttribute("etat", "modification");
               request.setAttribute("equipe", equipe);
               request.getRequestDispatcher("/WEB-INF/ValidationEquipe.jsp").forward(request, response);       
           
            break;
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
