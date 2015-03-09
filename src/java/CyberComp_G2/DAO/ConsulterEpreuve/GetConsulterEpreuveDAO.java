/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import static CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO.lesEquipes;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi
 */
public class GetConsulterEpreuveDAO {
    public static final String lesEpreuvesInv = "SELECT  idEpreuve,nomDiscipline,nomEpreuve,to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, categorie,nbDePlaceAchetées FROM viewEpreuve JOIN lesEpreuvesIndividuelles USING (idEpreuve)";
    public static final String lesEpreuvesEquipe= "SELECT idEpreuve,nomDiscipline,nomEpreuve,to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, categorie,nbPersonneFixe,0 FROM viewEpreuve JOIN lesEpreuvesParEquipe USING (idEpreuve)";
    public static final String lesDisciplines = "SELECT nomDiscipline FROM viewEpreuve GROUP BY (nomDiscipline)";
    
    public static CachedRowSet getEpreuvesInv() throws SQLException{
        return getConsulterEpreuve(lesEpreuvesInv);
    }

    public static CachedRowSet getEpreuvesEquipe() throws SQLException{
        return getConsulterEpreuve(lesEpreuvesEquipe);
    }
   
    public static CachedRowSet getDisciplines() throws SQLException{
        return getConsulterEpreuve(lesDisciplines);
    }
    
    
     private static CachedRowSet getConsulterEpreuve(String query) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query));
        crs.execute();
        return crs;
    }
}
