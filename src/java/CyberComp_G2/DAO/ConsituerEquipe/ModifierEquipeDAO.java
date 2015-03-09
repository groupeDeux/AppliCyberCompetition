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
            "INSERT INTO LesConstitutionsEquipes (idEquipe, idSportif) values ('%', '%')";
    
    public static CachedRowSet addSportif(Equipe equipe, Sportif sportif) 
            throws SQLException {
        return requeteConstitutionEquipe(insertSportif, 
                equipe.getIdEquipe(),
                sportif.getIdSportif());
    }
    
    public static final String deleteSportif = 
            "DELETE FROM LesConstitutionsEquipes WHERE idSportif=% AND idEquipe=%";
    
    public  static CachedRowSet delSportif(Equipe equipe, Sportif sportif)  
            throws SQLException {
        return requeteConstitutionEquipe(deleteSportif, 
                equipe.getIdEquipe(),
                sportif.getIdSportif());
    }
    
    private static CachedRowSet requeteConstitutionEquipe(String query, 
            int idEquipe, 
            int idSportif) 
            throws SQLException{
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEquipe, idSportif));
        crs.execute();
        return crs; 
    } 
    
    public static final String addEquipe = 
            "INSERT INTO LesEquipes(idEquipe, nomEquipe, categorie, nbMembre) " +
            "VALUES (%, '%', '%', %)";
   
    public static CachedRowSet addEquipe(Equipe equipe) throws SQLException {
        return requeteAddEquipe(insertSportif, 
                equipe.getIdEquipe(), 
                equipe.getNomEquipe(),
                equipe.getCategorie(),
                equipe.getNbMembre());
    }

    private static CachedRowSet requeteAddEquipe(String query, 
            int idEquipe, 
            String nomEquipe, 
            String categorie,
            int nbMembre) throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEquipe, nomEquipe, categorie, nbMembre));
        crs.execute();
        return crs; 
    }
    
    public static final String deleteEquipe = 
            "DELETE FROM LesEquipes WHERE idEquipe= %";
    
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


