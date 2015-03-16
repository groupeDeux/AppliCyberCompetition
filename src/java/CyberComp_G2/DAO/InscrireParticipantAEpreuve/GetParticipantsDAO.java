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
    
    /*------------------------------------------------------------------------
    /*---- Requete a mettre dans la fonction getEquipesCompatibles------------ 
    /* Recuperation categorie a passer en param aux autres requetes*/
    public static final String laCategorie
            = "SELECT E.categorie FROM LesEpreuves where E.idEpreuve= %d";
    
    /* Recuperation NbFixeSportif a passer en param aux autre requetes*/
    public static final String leNbPersonneFixe
            ="SELECT E.NbPersonneFixe FROM LesEpreuvesParEquipe where E.idEpreuve= %d";
    
    /*Selection des Equipes compatibles en cat et nbInscritsavec une epreuve: idEpreuve*/
    public static final String lesEquipesCompatiblesEpreuve
            = "Select V.idEquipe, V.nomEquipe, V.categorie,V.nbMembre P.IDPARTICIPANT,P.pays"
            + "from viewEquipe V"
            + "join LesParticipants P on (V.idEquipe=P.idParticipant)"
            + "where (V.NbMembre=%d and V.categorie=%s )";
    
    
       
     /*Selection des Sportifs compatibles en categorie avec une epreuve: idEpreuve*/
    public static final String lesSportifsCompatiblesEpreuveCat
            = "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant)"
            + "where S.genre= %s"; 

    /*Selection des Equipes inscrits à une épreuve: idEpreuve*/
    public static final String lesEquipesInscritesEpreuve
            = "SELECT * FROM viewEquipe E JOIN LesParticipants P on (E.idEquipe=P.idParticipant)"
            + " Join lesParticipations P2 on (E.idEquipe=P2.idParticipant) "
            + "where P2.idEpreuve= %d";

   
     /* Compatibles et non deja inscrits -------> METHODE a ecrire (requete avec 3 parametres */ 
    public static final String lesEquipesCompatiblesEtNonInscrites
            ="Select V.idEquipe, V.nomEquipe, V.categorie,V.nbMembre,P.IDPARTICIPANT,P.pays "
            + "from viewEquipe V "
            + "join LesParticipants P on (V.idEquipe=P.idParticipant) "
            + "where (V.NbMembre<%d and V.categorie=%s ) "
            +"minus "
            +"Select V.idEquipe, V.nomEquipe, V.categorie,V.nbMembre,P.IDPARTICIPANT,P.pays "
            + "from viewEquipe V "
            + "join LesParticipants P on (V.idEquipe=P.idParticipant) "
            + "join lesParticipations P2 "
            + "on (P2.idParticipant=P.idParticipant) "
            + "where (V.NbMembre<%d and V.categorie=%s and P2.idEpreuve=%s )";
  
    /*--------------------------------------------------------------------------
        
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

    public static CachedRowSet getLesSportifsCompatiblesEpreuveCat(int idEpreuve)  throws SQLException {
        return getConsulterParticipants(lesSportifsCompatiblesEpreuveCat,idEpreuve);
    }

    
    
    /* fonction pour sortir les equipes compatible en categorie et en nbMembre à une epreuve donnée*/
     public static CachedRowSet getLesEquipesCompatiblesEpreuve(int idEpreuve)throws SQLException {
         
         // categorie de l epreuve dans uen variable java
         CachedRowSet rowSetCategorie=getConsulterParticipants(laCategorie,idEpreuve);
         String categorie=rowSetCategorie.getString("categorie");
         // NbPersonneFixe de l epreuve dans une variable java
         CachedRowSet rowSetNbPersonneFixe=getConsulterParticipants(leNbPersonneFixe,idEpreuve);
         int nbPersonneFixe=rowSetNbPersonneFixe.getInt("NbPersonneFixe");      
         
        return getConsulterParticipants( lesEquipesCompatiblesEpreuve,categorie,nbPersonneFixe) ;
    }
     
      /* fonction pour sortir les equipes compatible en categorie et en nbMembre à une epreuve donnée*/
     public static CachedRowSet getLesEquipesCompatiblesEtNonInscrites(int idEpreuve)throws SQLException {
         // categorie de l epreuve dans uen variable java
         CachedRowSet rowSetCategorie=getConsulterParticipants(laCategorie,idEpreuve);
         String categorie=rowSetCategorie.getString("categorie");
         // NbPersonneFixe de l epreuve dans une variable java
         CachedRowSet rowSetNbPersonneFixe=getConsulterParticipants(leNbPersonneFixe,idEpreuve);
         int nbPersonneFixe=rowSetNbPersonneFixe.getInt("NbPersonneFixe");      
         
        return getConsulterParticipants( lesEquipesCompatiblesEtNonInscrites,categorie,nbPersonneFixe,idEpreuve) ;
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
    
    /*-------------------------------------------------------------
    Fonction appele pour construction des RowSet
    execute la "query"=requete avec les "selecteur"=parametres
    -------------------------------------------------------------*/
    private static CachedRowSet getConsulterParticipants(String query, String categorie,int nbFixe) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,categorie,nbFixe));
        crs.execute();
        return crs;
    }
    
    /*-------------------------------------------------------------
    Fonction appele pour construction des RowSet
    execute la "query"=requete avec les "selecteur"=parametres
    -------------------------------------------------------------*/
    private static CachedRowSet getConsulterParticipants(String query, String categorie,int nbFixe, int idEpreuve) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,categorie,nbFixe, idEpreuve));
        crs.execute();
        return crs;
    }
    
}
