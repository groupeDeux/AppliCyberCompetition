/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.DAO.ConsituerEquipe.ModifierEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Delegation;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author magourar
 */
@WebServlet(name = "GetListSportif", urlPatterns = {"/GetListSportif"})
public class SetListSportif extends HttpServlet {

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
            throws ServletException, IOException, ParseException, CategorieException {
        /* rowSetdelegation de type cachedRowSet
         =set resultat issu de requete BD garde en memoire meme sans connexion*/

        //1 on récupére tous les attributs de sportif à partif de la page HTML rempli par le secretaire 
        //2 on crée l'objet sportif 
        //3 on l'envoi au dao en appelant la methode  addSportif(sportif, equipe)
        StringBuilder rep = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String idSportifstr = request.getParameter("idSportif");
            int idSportif = Integer.parseInt(idSportifstr);
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissanceStr = request.getParameter("dateNaissance");
            Date dateNaissance = (Date) sdf.parse(dateNaissanceStr);
            String genre = request.getParameter("genre");
            String pays = request.getParameter("pays");
            String descriptionHandicap = request.getParameter("descriptionHandicap");
            Sportif s1 = new Sportif(idSportif, pays, prenom, nom, dateNaissance, genre, descriptionHandicap);

            //récupérer les informations relié à une équipe 
            int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
            String nomEquipe = request.getParameter ("nomEquipe");
            String categorie = request.getParameter ("categorie");
            Equipe e1 = new Equipe(idEquipe, nomEquipe, categorie,2);

            //envoi de l'objet au dao ModifierEquipeDAO en appelant la méthode addSportif
            ModifierEquipeDAO.addSportif(e1, s1);
        } catch (SQLException ex) {
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServletInfo() {
        return super.getServletInfo(); //To change body of generated methods, choose Tools | Templates.
    }

}
