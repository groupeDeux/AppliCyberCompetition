/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.ConsituerEquipe;

import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author oprisora
 */
public class ModifierEquipeDAO {

    public static final String insertSportif = 
            "INSERT INTO SportifEquipe (idSportif, idEquipe) values ('%', '%')";
    
    public static CachedRowSet addSportif(Sportif sportif, Equipe equipe) 
            throws SQLException {
        return requeteSportifEquipe(insertSportif, 
                sportif.getIdSportif(), 
                equipe.getIdEquipe());
    }
    
    public static final String deleteSportif = 
            "DELETE FROM SportifEquipe WHERE idSportif=% AND idEquipe=%";
    
    public  static CachedRowSet delSportif(Sportif sportif, Equipe equipe) 
            throws SQLException {
        return requeteSportifEquipe(deleteSportif, 
                sportif.getIdSportif(), 
                equipe.getIdEquipe());
    }
    
    private static CachedRowSet requeteSportifEquipe(String query, 
            int idSportif, 
            int idEquipe) 
            throws SQLException{
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idSportif, idEquipe));
        crs.execute();
        return crs; 
    } 
    
    public static final String addEquipe = 
            "INSERT INTO LesEquipes(idParticipant, nomEquipe, pays) " +
            "VALUES (%, %, %)";
   
    public static CachedRowSet addEquipe(Equipe equipe) throws SQLException {
        return requeteAddEquipe(insertSportif, 
                equipe.getIdParticipant(), 
                equipe.getNomEquipe(),
                equipe.getPays());
    }

    private static CachedRowSet requeteAddEquipe(String query, 
            int idParticipant, 
            String nomEquipe, 
            String pays) throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idParticipant, nomEquipe, pays));
        crs.execute();
        return crs; 
    }
    
    public static final String deleteEquipe = 
            "DELETE FROM LesEquipes WHERE idEquipe=%";
    
    public  static CachedRowSet delEquipe(Equipe equipe) throws SQLException {
        return requeteDelEquipe(deleteEquipe, equipe.getIdEquipe());
    }

    private static CachedRowSet requeteDelEquipe(String query, int idEquipe) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEquipe));
        crs.execute();
        return crs; 
    }
    
}


