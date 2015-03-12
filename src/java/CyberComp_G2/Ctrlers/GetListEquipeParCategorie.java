/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Ctrlers;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.rmi.server.LogStream.log;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 * Renvoi une liste d'objets java de classe Equipes
 * pour toutes les equipes de la competition
 * @author agathe
 */


@WebServlet(name = "GetListEquipe", urlPatterns = {"/GetListEquipe"})
public class GetListEquipeParCategorie {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder rep = new StringBuilder(); // On en a besoin ici?  
        String categorie = request.getParameter("categorie"); // qu'est ce que la request  ??
        
        try(PrintWriter out = response.getWriter()){
            
            // recuperation des donnees BD chargees avec DAO dans un rowSet
            CachedRowSet rowSetEquipesParCategorie=GetParticipantsDAO.getEquipesParCategorie(categorie);
            /*Parcours du rowSet pour creer la liste deroulante 
             * au format html    ?? 
             * rowSetEquipe.getString("pays") --> le row set connait le nom des colonnes ??
             */
           
            while(rowSetEquipesParCategorie.next()){
                String pays = rowSetEquipesParCategorie.getString("pays");
                String idEquipe = rowSetEquipesParCategorie.getString("idEquipe");
                rep.append("<option value='").append(rowSetEquipesParCategorie.getString("idEquipe")).append("'>").append(pays).append(" ").append(idEquipe).append("</option>");
            }
            out.println(rep);
        }catch (SQLException ex){
            log(ex.getMessage());
        }
    } 
    
}
