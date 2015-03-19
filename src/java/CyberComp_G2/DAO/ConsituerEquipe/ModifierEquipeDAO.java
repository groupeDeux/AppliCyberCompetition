/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.ConsituerEquipe;

import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import com.sun.rowset.CachedRowSetImpl;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author oprisora
 */
public class ModifierEquipeDAO {
     
    public static final String insertSportif = 
            "INSERT INTO LesConstitutionsEquipe (idEquipe, idSportif) values (%d, %d)";
    
    public static final String MaxIdEquipe = 
            "select max(idEquipe) from viewEquipe";
    
     public static final String insertParticipant = 
            "INSERT INTO LesParticipants values (%d, '%s')";
     
    public static final String deleteSportif = 
            "DELETE FROM LesConstitutionsEquipe WHERE  idEquipe= %d";
    
    public static final String addEquipe = 
            "INSERT INTO LesEquipes(idEquipe, nomEquipe, categorie) " +
            "VALUES (%d, '%s','%s')";
    
    public static final String deleteEquipe = 
            "DELETE FROM LesEquipes WHERE idEquipe= %d";
    
    public static final String deleteParticipant = 
            "DELETE FROM LesParticipants WHERE idParticipant= %d";
    
    public static final String deleteParticipation = 
            "DELETE FROM LesParticipations WHERE idParticipant= %d";
    
    /**
     * Ajout une equipe dans la base de donnée
     * @param datasource
     * @param equipe
     * @return
     * @throws SQLException 
     */
    public static int addEquipe(DataSource datasource ,Equipe equipe) throws SQLException {

        String nomEquipe = equipe.getNomEquipe();
        Connection conn = datasource.getConnection();
           String categorie =  equipe.getCategorie();
           int nbMembre = equipe.getNbMembre();
           String pays = equipe.getPays();
           int idEquipe=0;
          
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(MaxIdEquipe);
           while(rs.next()){
              idEquipe = rs.getInt(1) +1 ;
           }


           try{
             conn.setAutoCommit(false);
             stmt.executeUpdate(String.format(insertParticipant,idEquipe,pays)); 
             stmt.executeUpdate(String.format(addEquipe,idEquipe,nomEquipe,categorie));
             int i;
             for(i=0;i<nbMembre;i++){
                 stmt.executeUpdate(String.format(insertSportif,idEquipe,equipe.getLesMembres().get(i).getIdSportif()));
             }
             conn.commit();
             conn.setAutoCommit(true);
           }catch(SQLException ex){
              conn.rollback();
              String erreur = ex.getMessage();
              int i=0;
           }
         return idEquipe;  
    }
    /**
     * modifie une equipe presente dans la base de donnée
     * @param datasource
     * @param equipe
     * @throws SQLException 
     */
    public static void modifEquipe(DataSource datasource ,Equipe equipe) throws SQLException {

        String nomEquipe = equipe.getNomEquipe();
        Connection conn = datasource.getConnection();
        
           int nbMembre = equipe.getNbMembre();
           int idEquipe= equipe.getIdEquipe();
          
           Statement stmt = conn.createStatement();

           try{
             conn.setAutoCommit(false);
             stmt.executeUpdate(String.format(deleteSportif,idEquipe)); 
             int i;
             for(i=0;i<nbMembre;i++){
                 stmt.executeUpdate(String.format(insertSportif,idEquipe,equipe.getLesMembres().get(i).getIdSportif()));
             }
             conn.commit();
             conn.setAutoCommit(true);
           }catch(SQLException ex){
              conn.rollback();
              String erreur = ex.getMessage();
              int i=0;
           }
           
    }
    /**
     * 
     * Suprime une equipe de la base de donnée
     * @param datasource
     * @param equipe
     * @throws SQLException 
     */
    public static void SupprimerEquipe(DataSource datasource ,Equipe equipe) throws SQLException {

        
        Connection conn = datasource.getConnection();
        
           int idEquipe= equipe.getIdEquipe();
          
           Statement stmt = conn.createStatement();

           try{
             conn.setAutoCommit(false);
             stmt.executeUpdate(String.format(deleteSportif,idEquipe)); 
             stmt.executeUpdate(String.format(deleteEquipe,idEquipe));
             stmt.executeUpdate(String.format(deleteParticipation,idEquipe));
             stmt.executeUpdate(String.format(deleteParticipant,idEquipe));
            conn.commit();
             conn.setAutoCommit(true);
           }catch(SQLException ex){
              conn.rollback();
              throw ex;
           }
           
    }
    
}


