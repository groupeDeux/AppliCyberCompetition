/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.ConsulterEpreuve;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author fureta
 */
public class GetMedaillesDAO {
    
    /* Retourne la liste des equipes medaillees a une epreuve donnes */
    private static final String lesEquipesMedailleesParEpreuve
            = "select * from LESMEDAILLES M "
            + "join lesEquipes E "
            + "on M.idParticipant=E.idEquipe "
            + " where idEpreuve=%d ";
    
    /* Retourne la liste des equipes medaillees a une epreuve donnes */
    private static final String lesSportifsMedaillesParEpreuve
            = "select * from LESMEDAILLES M "
            + "join lesSportifs S "
            +"on M.idParticipant=S.idSportif "
            + " where idEpreuve=%d ";

    
    
    //methodes
     public static CachedRowSet getListEquipesMedaillees(int idEpreuve) throws SQLException {
        return getMedaillesParEpreuve(lesEquipesMedailleesParEpreuve, idEpreuve);
    }
     
     public static CachedRowSet getListSportifsMedailles(int idEpreuve) throws SQLException {
        return getMedaillesParEpreuve(lesSportifsMedaillesParEpreuve, idEpreuve);
    }
     
      private static CachedRowSet getMedaillesParEpreuve(String query, int idEpreuve) throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEpreuve));
        crs.execute();
        return crs;
    }
    
}


