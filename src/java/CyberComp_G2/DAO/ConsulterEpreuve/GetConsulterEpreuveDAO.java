/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi, oprisora
 */
public class GetConsulterEpreuveDAO {

    private static final String estEpreuveIndividuelle = "SELECT idepreuve from lesEpreuvesIndividuelles WHERE idepreuve=%d";

    /* Requete de recherche de toutes les epreuves dans la base de donnée */
    public static final String lesEpreuves
            = "Select idepreuve, nomDiscipline, nomEpreuve, "
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'), "
            + "urlVideo,tarif,nbDePlace,categorie FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) "
            + "union "
            + "Select idepreuve, nomDiscipline, nomEpreuve, to_char(dateDebut, "
            + "'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo, "
            + "tarif,nbDePlace,categorie FROM viewEpreuve JOIN lesEpreuvesParEquipe "
            + "USING (idEpreuve)";


    /* Requete de recherche d'une épreuve en fonction d'un ID, utilisé notamment dans le
     panier
     */
    public static final String lesEpreuvesParId
            = "Select idepreuve, nomDiscipline, nomEpreuve, "
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'), "
            + "urlVideo,tarif,nbDePlace,categorie ,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) where (idEpreuve= %d) "
            + "union "
            + "Select idepreuve, nomDiscipline, nomEpreuve, to_char(dateDebut, "
            + "'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo, "
            + "tarif,nbDePlace,categorie,nbDePlaceAchetées FROM viewEpreuve JOIN lesEpreuvesParEquipe "
            + "USING (idEpreuve) where ( idEpreuve = %d )";

    /*
     Requête qui retourne la liste des épreuves individuelles avec les informations
     suivante : ...
     */
    public static final String lesEpreuvesInv
            = "SELECT  idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) "
            + "order by nomDiscipline";

    /*
     /!\ Dans cette requete on spécifie le nom de la discipline
     Requête qui retourne la liste des épreuves individuelles correspondant à un nom
     de discipline avec un avec les informations
     suivante : ...
     */
    public static final String lesEpreuvesParDisciplineInv
            = "SELECT  idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) where (nomDiscipline = '%s') "
            + "order by nomEpreuve";

    /*
     Requête qui retourne la liste des épreuves par equipe avec les informations
     suivante :...
    
     */
    public static final String lesEpreuvesEquipe
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbPersonneFixe,0 FROM viewEpreuve"
            + " JOIN lesEpreuvesParEquipe USING (idEpreuve)"
            + " order by nomDiscipline";

    /*
     /!\ Dans cette requete on spécifie le nom de la discipline
     Requête qui retourne la liste des épreuves par équipe correspondant à une 
     discipline spécifique.
     */
    public static final String lesEpreuvesParDisciplineEquipe
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbPersonneFixe,0 FROM viewEpreuve "
            + "JOIN lesEpreuvesParEquipe USING (idEpreuve) where (nomDiscipline = '%s') "
            + "order by nomEpreuve";

    /*
     Requete qui permet de retourner la liste des disciplines
     */
    public static final String lesDisciplines
            = "SELECT nomDiscipline FROM viewEpreuve GROUP BY (nomDiscipline)";

    /*
     Requete qui retourne la liste des medailles associé à chaque sportif.
     */
    public static final String lesMedaillesDesSportifs
            = "SELECT nomEpreuve, nom, prenom, pays, valeur FROM LesMedailles "
            + "natural join LesParticipants P join LesSportifs S "
            + "ON (P.idParticipant=S.idSportif) WHERE idEpreuve = % "
            + "ORDER BY valeur asc ";

    /*
     Requete qui retourne la liste des medailles par equipes.
     */
    public static final String lesMedaillesDesEquipes
            = "SELECT nomEquipe, categorie, valeur FROM LesMedailles M "
            + "join LesEquipes E ON (M.idParticipant=E.idEquipe) "
            + "WHERE idEquipe = %d "
            + "ORDER BY valeur asc ";

    public static final String lesEpreuveSsResulats
            = "select idepreuve, nomDiscipline, nomEpreuve, to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace,categorie "
            + "from lesEpreuves "
            + "minus "
            + "select idepreuve, nomDiscipline, nomEpreuve, to_char(dateDebut,'DD-MM-YYYY HH24'),to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace,categorie "
            + "from lesMedailles "
            + "join lesEpreuves "
            + "Using(idEpreuve)";
    public static final String EpreuvesIndSansResulat
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve, "
            + " to_char(dateDebut,'DD-MM-YYYY HH24'), "
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + " categorie, nbdeplaceachetées "
            + "from lesEpreuvesIndividuelles "
            + "join viewEpreuve "
            + " USING (idEpreuve) "
            + " minus "
            + " SELECT idEpreuve,nomDiscipline,nomEpreuve, "
            + "to_char(dateDebut,'DD-MM-YYYY HH24'), "
            + " to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + " categorie,nbdeplaceachetées "
            + " FROM lesEpreuvesIndividuelles "
            + " JOIN LESMEDAILLES "
            + "USING (idEpreuve) "
            + " join viewEpreuve "
            + "USING (idEpreuve) "
            + "order by idEpreuve";
    public static final String EpreuvesEquipeSansResulat
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve, "
            + " to_char(dateDebut,'DD-MM-YYYY HH24'), "
            + " to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie, nbdeplaceachetées "
            + "from lesEpreuvesParEquipe "
            + "  join viewEpreuve "
            + " USING (idEpreuve) "
            + " minus "
            + " SELECT idEpreuve,nomDiscipline,nomEpreuve, "
            + " to_char(dateDebut,'DD-MM-YYYY HH24'), "
            + " to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + " categorie,nbdeplaceachetées "
            + " FROM lesEpreuvesParEquipe "
            + " JOIN LESMEDAILLES "
            + " USING (idEpreuve) "
            + " join viewEpreuve "
            + "USING (idEpreuve) "
            + " order by idEpreuve ";

    /*
    
     */
    public static CachedRowSet getEpreuvesParDisciplineInv(String nomDiscipline) throws SQLException {
        return getConsulterEpreuveParDiscipline(lesEpreuvesParDisciplineInv, nomDiscipline);
    }

    public static CachedRowSet getEpreuvesParDisciplineEquipe(String nomDiscipline) throws SQLException {
        return getConsulterEpreuveParDiscipline(lesEpreuvesParDisciplineEquipe, nomDiscipline);
    }

    public static CachedRowSet getEpreuvesInv() throws SQLException {
        return getConsulterEpreuve(lesEpreuvesInv);
    }

    public static CachedRowSet getEpreuvesEquipe() throws SQLException {
        return getConsulterEpreuve(lesEpreuvesEquipe);
    }

    /**
     * retourne un Cachedrowset des epreuves individuelles qui n'ont pas encore
     * de resulat
     *
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getEpreuvesIndSansResulat() throws SQLException {
        return getConsulterEpreuve(EpreuvesIndSansResulat);
    }

    /**
     * retourne un Cachedrowset des epreuves par equipe qui n'ont pas encore de
     * resulat
     *
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getEpreuvesEquipeSansResulat() throws SQLException {
        return getConsulterEpreuve(EpreuvesEquipeSansResulat);
    }

    public static CachedRowSet getDisciplines() throws SQLException {
        return getConsulterEpreuve(lesDisciplines);
    }

    public static CachedRowSet getMedaillesDesSportifs(int idEpreuve)
            throws SQLException {
        return getConsulterEpreuveAvecSelecteur(lesMedaillesDesSportifs, idEpreuve);
    }

    public static CachedRowSet getMedaillesDesEquipes(int idEquipe)
            throws SQLException {
        return getConsulterEpreuveAvecSelecteur(lesMedaillesDesEquipes, idEquipe);
    }

    public static CachedRowSet getEpreuvesParId(int idEquipe)
            throws SQLException {
        return getConsulterEpreuveAvecSelecteur(lesEpreuvesParId, idEquipe);
    }

    private static CachedRowSet getConsulterEpreuve(String query)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query));
        crs.execute();
        return crs;
    }

    private static CachedRowSet getConsulterEpreuveAvecSelecteur(String query,
            int selecteur)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, selecteur, selecteur));
//        crs.setInt(1, selecteur);
//        crs.setInt(2, selecteur);
        crs.execute();
        return crs;
    }

    private static CachedRowSet getConsulterEpreuveParDiscipline(String query, String nomDiscipline)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, nomDiscipline));
        crs.execute();
        return crs;
    }

    public Epreuve getEpreuve(int idEpreuve) throws SQLException, CategorieException, nbPlaceAcheterExeception {
        ResultSet rowSetEpreuve = new GetConsulterEpreuveDAO().getEpreuvesParId(idEpreuve);

        boolean estIndividuelle = false;

        if (rowSetEpreuve.next()) {

            CachedRowSet crs = new CachedRowSetImpl();
            crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
            crs.setCommand(String.format(estEpreuveIndividuelle, idEpreuve));
//        crs.setInt(1, selecteur);
//        crs.setInt(2, selecteur);
            crs.execute();
            if (crs.next()) {
                estIndividuelle = true;
            }

            return new Epreuve(rowSetEpreuve.getInt(1),
                    rowSetEpreuve.getString(3), rowSetEpreuve.getString(2),
                    rowSetEpreuve.getString(4), rowSetEpreuve.getString(5),
                    rowSetEpreuve.getString(6), rowSetEpreuve.getDouble(7),
                    rowSetEpreuve.getInt(8), rowSetEpreuve.getString(9),
                    rowSetEpreuve.getInt(10), estIndividuelle);
        }
        return null;
    }
}
