/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.InscrireParticipantAEpreuve;

import static CyberComp_G2.DAO.InscrireParticipantAEpreuve.GetParticipantsDAO.lesEquipesInscritesEpreuve;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author agathe
 */
public class ModifierParticipationsDAO {
    
    /**************************************************************************/
    /*Les requetes SQL*/
    /**************************************************************************/
    
    /*Inserer une participation*/
    public static final String insertParticipant = 
            "INSERT INTO LesParticipations (idEpreuve,idParticipant) values (%d, %d)";
 
    /**************************************************************************/
    /*Les fonctions appelees dans les controler avec les bons parametres */
    /**************************************************************************/
     /**
     * insert un participant a une epreuve
     *
     * @param idEpreuve
     * @param idParticipant
     * @return
     * @throws SQLException
     */
    public static CachedRowSet ajouterParticipantUnique(int idEpreuve, int idParticipant)
            throws SQLException {
        return modifierParticipation(insertParticipant, idEpreuve, idParticipant);
    }
    
    
    
     /**************************************************************************/
    /*Les fonctions pour executer les requetes*/
    /**************************************************************************/
      private static CachedRowSet modifierParticipation(String query, int idEpreuve, int idParticipant)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEpreuve, idParticipant));
        crs.execute();
        return crs;
    }
}
