/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.ConsituerEquipe;

import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import com.sun.rowset.CachedRowSetImpl;
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
    
    @Resource (name="jdbc/BDCyberCompetition")
    private static DataSource dataSource = new InitialContext().;
     
    public static final String insertSportif = 
            "INSERT INTO LesConstitutionsEquipes (idEquipe, idSportif) values (%d, %d)";
    
    public static final String MaxIdEquipe = 
            "select max(idEquipe) from viewEquipe";
    
    public static final String deleteSportif = 
            "DELETE FROM LesConstitutionsEquipes WHERE idSportif=%d AND idEquipe= %d";
    
    public static final String addEquipe = 
            "INSERT INTO LesEquipes(idEquipe, nomEquipe, categorie) " +
            "VALUES (%d, '%s','%s)";
    
    public static final String deleteEquipe = 
            "DELETE FROM LesEquipes WHERE idEquipe= %d";
    
    
    public static void addEquipe(Equipe equipe) throws SQLException {
          String nomEquipe = equipe.getNomEquipe();
           String categorie =  equipe.getCategorie();
           int nbMembre = equipe.getNbMembre();
           int idEquipe ;
        
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(MaxIdEquipe);
           while(rs.next()){
              idEquipe = rs.getInt(1);
           }
    }

    
}


