/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsituerEquipe;


import CyberComp_G2.utils.ConnexionBD;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 * Gère les requêtes relatives à la constitution d'une équipe
 * @author vivi, oprisora
 */
public class GetConsulterEquipeDAO {
    
    public static final String lesDelegations = 
            "SELECT * FROM viewDelegation order by pays";
    
    public static final String lesEquipesDUneDelegation = 
            "SELECT * FROM viewEquipe E JOIN LesParticipants P "
            + "on (E.idEquipe=P.idParticipant) WHERE pays ='%s' order by idEquipe";
    
    public static final String lesSportifsSelonGenre = 
            "SELECT * FROM LesSportifs E JOIN LesParticipants P "
            + "ON (E.idSportif = P.idParticipant) WHERE pays='%s' AND genre='%s'";
    
    public static final String lesSportifsDUneEquipe = 
            "SELECT * FROM LESCONSTITUTIONSEQUIPE join LESSPORTIFS  USING (idSportif) join viewEquipe USING(idEquipe)  join lesParticipants on (idEquipe = idParticipant)"
            + "where idEquipe =%d order by nom";
    
    public static final String lesSportifsDUneDelegation = 
            "SELECT * FROM LesSportifs S JOIN LesParticipants P"
            + " on (S.idSportif=P.idParticipant) WHERE pays='%s' order by nom";
    
    public static final String lesEquipesSupprimable
            = "select idEquipe, nomEquipe,categorie,nbMembre "+
            "from viewEquipe "+
            "join lesParticipants "+
            "on (idEquipe = idParticipant) "+
            "where pays ='%s' "+
            "minus "+
            "select idEquipe, nomEquipe,categorie,nbMembre "+ 
            "from viewEquipe "+
            "join LESCONSTITUTIONSEQUIPE "+
            "USING (idEquipe) "+
            "join lesMedailles "+
            "on(idEpreuve=idEpreuve and idParticipant = idEquipe) ";
    
    
    public static  final String lesEpreuveANbFixeInCoherent 
            = "select idEpreuve,NBPERSONNEFIXE "
            + "from LesEquipes "
            + "join LesParticipations ON (IdParticipant = IdEquipe) "
            + "join LesEpreuvesParEquipe USING (idEpreuve) "
            + "where (IDEQUIPE = %d and NBPERSONNEFIXE is not null and NBPERSONNEFIXE <> %d)";
    
    public static  final String lesEpreuveAvecResultatEtEquipeInscrite
            ="Select distinct IDEPREUVE "
            + "from LESPARTICIPATIONS LPa "
            + "join LESMEDAILLES lMe "
            + "using(IdEpreuve) "
            + "join LESEQUIPES "
            + "on(LPa.IDPARTICIPANT=IDEQUIPE) "
            + "where IDEQUIPE= %d";
    
    /**
     *  retourne la liste des |Delegation|s
     * @return
     * @throws SQLException 
     */
    public CachedRowSet getDelegations() throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesDelegations);
    }

    /**
     * retourne les Epreuves ou est inscrite l'equipe et dont les resultat sont connue (terminer)
     * @param idEquipe
     * @return
     * @throws SQLException 
     */
    public CachedRowSet getEpreuveAvecResultatDeLEquipe( int idEquipe) 
            throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesEpreuveAvecResultatEtEquipeInscrite,idEquipe);
    }
     
    /**
     * retourne idEpreuve et le nbPersonnesfixe de l'|Epreuve| si celui ci est incohérent avec le nombre de membre dans l'|Equipe|
     * @param idEquipe
     * @param nbMembre
     * @return
     * @throws SQLException 
     */
    public CachedRowSet getEpreuveANbInCoherent(int idEquipe, int nbMembre) throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesEpreuveANbFixeInCoherent,idEquipe,nbMembre);
    }
    /**
     * retroune la liste des |Equipe|s d'une |Delegation| donnée
     * @param pays
     * @return
     * @throws SQLException 
     */
    
    public CachedRowSet getEquipesDUneDelegation(String pays)
            throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesEquipesDUneDelegation, pays);
    }
    
    /**
     * retourne la liste des |Sportif|s du même |Genre| faisant partie de la même |Delegation| 
     * @param pays
     * @param genre
     * @return
     * @throws SQLException 
     */
    public  static CachedRowSet getSportifsSelonGenre(String pays, String genre)
            throws SQLException {
        return ConnexionBD.INSTANCE.executeRequete(lesSportifsSelonGenre, pays, genre);
    }
    /**
     * retourne la liste des |Sportif|s d'une |Equipe|
     * @param idEquipe
     * @return
     * @throws SQLException
     */
    
    public static CachedRowSet getSportifsDUneEquipe(int idEquipe)
            throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesSportifsDUneEquipe, idEquipe);
    }

    /**
     * retroune la liste des |Sportif|s d'une |Delegation|
     * @param pays
     * @return
     * @throws SQLException
     */

    public static CachedRowSet getSportifsDUneDelegation(String pays) 
            throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesSportifsDUneDelegation, pays);
    }
    
    /**
     * retroune la liste des |Sportif|s qui ne sont pas inscrits à une |Epreuve| terminée
     * @param delegation
     * @return
     * @throws SQLException 
     */
    public CachedRowSet getEquipeSup(String delegation) throws SQLException{
        return ConnexionBD.INSTANCE.executeRequete(lesEquipesSupprimable , delegation);
    }
}