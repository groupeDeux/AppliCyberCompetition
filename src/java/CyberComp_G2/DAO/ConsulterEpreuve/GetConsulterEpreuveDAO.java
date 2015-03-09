/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi, oprisora
 */
public class GetConsulterEpreuveDAO {
    public static final String lesEpreuvesInv = 
            "SELECT  idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve)";
   
    public static final String lesEpreuvesEquipe= 
            "SELECT idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbPersonneFixe,0 FROM viewEpreuve"
            + " JOIN lesEpreuvesParEquipe USING (idEpreuve)";
    
    public static final String lesDisciplines = 
            "SELECT nomDiscipline FROM viewEpreuve GROUP BY (nomDiscipline)";
    
    public static final String lesMedaillesDesSportifs = 
            "SELECT nomEpreuve, nom, prenom, pays, valeur FROM LesMedailles "
            + "natural join LesParticipants P join LesSportifs S "
            + "ON (P.idParticipant=S.idSportif) WHERE idEpreuve = % "
            + "ORDER BY valeur asc ";
    
    public static final String lesMedaillesDesEquipes = 
            "SELECT nomEquipe, categorie, valeur FROM LesMedailles M "
            + "join LesEquipes E ON (M.idParticipant=E.idEquipe) "
            + "WHERE idEquipe = % "
            + "ORDER BY valeur asc ";
    
    public static CachedRowSet getEpreuvesInv() throws SQLException{
        return getConsulterEpreuve(lesEpreuvesInv);
    }

    public static CachedRowSet getEpreuvesEquipe() throws SQLException{
        return getConsulterEpreuve(lesEpreuvesEquipe);
    }
   
    public static CachedRowSet getDisciplines() throws SQLException{
        return getConsulterEpreuve(lesDisciplines);
    }
    
    public static CachedRowSet getMedaillesDesSportifs(int idEpreuve)
            throws SQLException {
        return getConsulterMedailleParEpreuve(lesMedaillesDesSportifs, idEpreuve); 
    }
    
    public static CachedRowSet getMedaillesDesEquipes(int idEquipe)
            throws SQLException {
        return getConsulterMedailleParEpreuve(lesMedaillesDesEquipes, idEquipe);
    }
    
     private static CachedRowSet getConsulterEpreuve(String query) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query));
        crs.execute();
        return crs;
    }
     
     private static CachedRowSet getConsulterMedailleParEpreuve(String query, 
             int selecteur) 
             throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, selecteur));
        crs.execute();
        return crs;
     } 
}
