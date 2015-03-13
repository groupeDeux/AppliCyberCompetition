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
            "INSERT INTO LesConstitutionsEquipes (idEquipe, idSportif) values (%d, %d)";
    
    public static final String MaxIdEquipe = 
            "select max(idEquipe) from viewEquipe";
    
     public static final String insertParticipant = 
            "INSERT INTO LesParticipants (idParticipant, pays) values (98, 'france')";
     
    public static final String deleteSportif = 
            "DELETE FROM LesConstitutionsEquipes WHERE idSportif=%d AND idEquipe= %d";
    
    public static final String addEquipe = 
            "INSERT INTO LesEquipes(idEquipe, nomEquipe, categorie) " +
            "VALUES (%d, '%s','%s')";
    
    public static final String deleteEquipe = 
            "DELETE FROM LesEquipes WHERE idEquipe= %d";
    
    
    public static void addEquipe(DataSource datasource ,Equipe equipe) throws SQLException {
         Connection conn2 =null;
        conn2 = datasource.getConnection();
             //conn2.setAutoCommit(false);
             Statement stmt2 = conn2.createStatement();
             stmt2.executeUpdate(insertParticipant);  
        String nomEquipe = equipe.getNomEquipe();
//           Connection conn = datasource.getConnection();
           String categorie =  equipe.getCategorie();
           int nbMembre = equipe.getNbMembre();
           String pays = equipe.getPays();
           int idEquipe=0;
          
//           Statement stmt = conn.createStatement();
//           ResultSet rs = stmt.executeQuery(MaxIdEquipe);
//           while(rs.next()){
//              idEquipe = rs.getInt(1) +1 ;
//           }
//           conn.close();

           try{
             
             stmt2.executeUpdate(String.format(addEquipe,idEquipe,nomEquipe,categorie));
             int i;
             for(i=0;i<nbMembre;i++){
                 stmt2.executeUpdate(String.format(insertSportif,idEquipe,equipe.getLesMembres().get(i).getIdSportif()));
             }
             conn2.commit();
             conn2.setAutoCommit(true);
           }catch(SQLException ex){
              conn2.rollback();
              String erreur = ex.getMessage();
              int i=0;
           }
           
    }

    
}


