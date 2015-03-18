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
    /*surpprimer une participation d'une equipe à une epreuve */
     public static final String deleteParticipant = 
            "DELETE FROM LesParticipations WHERE (idEpreuve=%d and idParticipant=%d )";
     // inserer un sportif à une epreuve individuelle 
   public static final String  insertSportif=
           "INSERT INTO LesParticipations (idEpreuve,idParticipant) values (%d, %d)";
   /*surpprimer la participation d'un sportif d'une  epreuve */
     public static final String deleteSportif = 
            "DELETE FROM LesParticipations WHERE (idEpreuve=%d and idParticipant=%d )";
 
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
    /**
     * inserer un sportif à un epreuve individuelle 
     * @param idEpreuve
     * @param idParticipant
     * @return
     * @throws SQLException 
     */
     public static CachedRowSet ajouterSportifUnique(int idEpreuve, int idParticipant)
            throws SQLException {
        return modifierParticipation(insertSportif, idEpreuve, idParticipant);
    }
    /**
     * delete un participant a une epreuve
     *
     * @param idEpreuve
     * @param idParticipant
     * @return
     * @throws SQLException
     */
    public static CachedRowSet supprimerParticipantUnique(int idEpreuve, int idParticipant)
            throws SQLException {
        return modifierParticipation(deleteParticipant, idEpreuve, idParticipant);
    }
    /**
     * suprimer l'inscription d'un sportif à une epreuve 
     * @param idEpreuve
     * @param idParticipant
     * @return
     * @throws SQLException 
     */
     public static CachedRowSet supprimerSportifUnique(int idEpreuve, int idParticipant)
            throws SQLException {
        return modifierParticipation(deleteSportif, idEpreuve, idParticipant);
    }
    
    
     /**************************************************************************/
    /*Les fonctions pour executer les requetes*/
    /**************************************************************************/
      // deux parametres int
    private static CachedRowSet modifierParticipation(String query, int idEpreuve, int idParticipant)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEpreuve, idParticipant));
        crs.execute();
        return crs;
    }
    
      
}
