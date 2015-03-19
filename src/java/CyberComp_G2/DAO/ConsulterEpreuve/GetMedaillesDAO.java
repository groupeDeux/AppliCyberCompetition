/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.ConstituerEquipe.Participant;
import CyberComp_G2.Model.ConstituerEquipe.Sportif;
import CyberComp_G2.Model.ConsulterEpreuve.Resultat;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author fureta
 */
public class GetMedaillesDAO {

    /* Retourne la liste des equipes medaillees a une epreuve donnes */
    private static final String lesEquipesMedailleesParEpreuve
            = "select * from LESMEDAILLES M "
            + "join viewEquipe E "
            + "on M.idParticipant=E.idEquipe "
            +"join lesParticipants P "
            + "on P.idParticipant=E.idEquipe "
            + " where idEpreuve=%d order by VALEUR";

    /* Retourne la liste des equipes medaillees a une epreuve donnes */
    private static final String lesSportifsMedaillesParEpreuve
            = "select * from LESMEDAILLES M "
            + "join lesSportifs S "
            + "on M.idParticipant=S.idSportif "
            +"join lesParticipants P "
            + "on P.idParticipant=S.idSportif "
            + " where idEpreuve=%d order by VALEUR";

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
    
    /**
     * renvoie le résultat d'une épreuve. null si l'épreuve n'a pas encore de résultat
     * @param idEpreuve
     * @param individuelle
     * @return
     * @throws SQLException
     * @throws CategorieException 
     */
    public static Resultat getResultat(int idEpreuve, boolean individuelle) throws SQLException, CategorieException {
        //Participant or,argent,bronze;
        Resultat res = null;
        
        if (individuelle) {
            CachedRowSet crs = getMedaillesParEpreuve(lesSportifsMedaillesParEpreuve, idEpreuve);
            List<Sportif> medailles = new ArrayList<>();
            while (crs.next()) {
                medailles.add(new Sportif(crs.getInt("idsportif"), crs.getString("pays"), crs.getString("prenom"), crs.getString("nom"), null, crs.getString("genre")));
            }
            if (! medailles.isEmpty()) {
                res=  new Resultat(medailles.get(2),medailles.get(0),medailles.get(1));
            }
            
        } else {
            CachedRowSet crs = getMedaillesParEpreuve(lesEquipesMedailleesParEpreuve, idEpreuve);
            List<Equipe> medailles = new ArrayList<>();
            while (crs.next()) {
                medailles.add(new Equipe(crs.getInt("idEquipe"), crs.getString("pays"), crs.getString("categorie"), crs.getInt("nbMembre")));
            }
            if (! medailles.isEmpty()) {
                res=  new Resultat(medailles.get(2),medailles.get(0),medailles.get(1));
            }
            
        }
        return res;
    }

}
