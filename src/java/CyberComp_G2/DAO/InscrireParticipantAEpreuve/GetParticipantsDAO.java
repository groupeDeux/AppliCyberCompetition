/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.InscrireParticipantAEpreuve;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 * Classe pour recuperer
 * @author agathe
 */
public class GetParticipantsDAO {
    /*-------------------------------------------------------------*/
    /*--------- Variables String contenant les requetes sql --------*/
    /*-------------------------------------------------------------*/
    
    /*Selection de tous les participants(idParticipant,pays)*/
    public static final String lesParticipants = 
            "SELECT * FROM LesParticipants order by idParticipant";
    /* Selection de toutes les equipes(idEquipe,nomEquipe,categorie,idParticipant,pays) */
    public static final String lesEquipes = 
            "SELECT * FROM LesEquipes E JOIN LesParticipants P on (E.idEquipe=P.idParticipant)";
    /* Selection de tous les sportifs
    (idSportif,nom,prenom,dateNaiss,genre,DescriptionHandicap,numChambre,nomBat,idParticipant,pays*/
    public static final String lesSportifs = 
            "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant)";
    /* Selection des equipes par categorie: feminin/masculin/mixte*/
    public static final String lesEquipesParCategorie = 
            "SELECT * FROM LesEquipes E JOIN LesParticipants P on (E.idEquipe=P.idParticipant) where E.categorie='%s";
    /* Selection des sportifs par genre: feminin/masculin*/
    public static final String lesSportifsParGenre = 
            "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant) where S.genre='%s";
    
    
    
    /* Recuperation categorie a passer en param aux autres requetes*/
    public static final String laCategorie
            = "SELECT E.categorie FROM LesEpreuves where E.idEpreuve= %d";
    
    /* Recuperation NbDePlace a passer en param aux autre requetes*/
    public static final String leNbDePlace
            = "SELECT E.NbDePlace FROM LesEpreuves where E.idEpreuve= %d";
        
     /*Selection des Sportifs compatibles en categorie avec une epreuve: idEpreuve*/
    public static final String lesSportifsCompatiblesEpreuve
            = "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant)"
            + "where S.genre= %s";

    /*Selection des Equipes compatibles en cat et nbInscritsavec une epreuve: idEpreuve*/
    public static final String lesEquipesCompatiblesEpreuveNb
            = "Select R1.idEquipe, E.nomEquipe, E.categorie, P.IDPARTICIPANT,P.pays from"
            + "(select idEquipe,count(idSportif) as NbSportifs from LesConstitutionsEquipe group by idEquipe) R1"
            + "join LesEquipes E on (R1.idEquipe=E.idEquipe) join LesParticipants P on (E.idEquipe=P.idParticipant)"
            + "where (NbSportifs=%d and E.categorie='%s)";

    /*Selection des Equipes inscrits à une épreuve: idEpreuve*/
    public static final String lesEquipesInscritesEpreuve
            = "SELECT * FROM LesEquipes E JOIN LesParticipants P on (E.idEquipe=P.idParticipant)"
            + " Join lesParticipations P2 on (E.idEquipe=P2.idParticipant) "
            + "where P2.idEpreuve= %d";

        
        
    /*-------------------------------------------------------------
    Construction des RowSet: appel d'une focntion 
    avec une requete et un parametre 
    -------------------------------------------------------------*/
    
     /*  retourne la liste des delegations
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getParticipants() throws SQLException{
        return getConsulterParticipants(lesParticipants,"");
    }
    /**
     * retourne la liste de toutes les equipes
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getEquipes() 
            throws SQLException{
        return getConsulterParticipants(lesEquipes,"");
    }
    
    /**
     * retourne la liste de tous les sportifs
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getSportifs() 
            throws SQLException{
        return getConsulterParticipants(lesSportifs, "");
    }
    
     /**
     * retourne la liste de toutes les equipes
     * @param categorie
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getEquipesParCategorie(String categorie) 
            throws SQLException{
        return getConsulterParticipants(lesEquipesParCategorie,categorie);
    }
    
     /**
     * retourne la liste de toutes les equipes
     * @param categorie
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getSportifsParGenre(String categorie) 
            throws SQLException{
        return getConsulterParticipants(lesSportifsParGenre,categorie);
    }
    
     /**
     * retourne la liste des equipe inscrite a une epreuve
     * @param idEpreuve
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getEquipesInscrites(int idEpreuve) 
            throws SQLException{
        return getConsulterParticipants(lesEquipesInscritesEpreuve,idEpreuve);
    }
    
    
    /*-------------------------------------------------------------
    Fonction appelee pour construction des RowSet
    execute la "query"=requete avec un "selecteur"=parametre (string)
    -------------------------------------------------------------*/
    private static CachedRowSet getConsulterParticipants(String query, String selecteur) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,selecteur));
        crs.execute();
        return crs;
    }
  /*-------------------------------------------------------------
    Fonction appele pour construction des RowSet
    execute la "query"=requete avec un "selecteur"=parametre (int)
    -------------------------------------------------------------*/
    private static CachedRowSet getConsulterParticipants(String query, int selecteur) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,selecteur));
        crs.execute();
        return crs;
    }
    
}
