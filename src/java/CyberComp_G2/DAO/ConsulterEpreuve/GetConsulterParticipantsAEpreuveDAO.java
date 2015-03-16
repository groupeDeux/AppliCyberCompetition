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
 * @author Gato
 */
public class GetConsulterParticipantsAEpreuveDAO {

    /* Retourne la liste des participants a une epreuve individuelle */
    private static final String lesParticipantsIndiv
            = "select P.IDPARTICIPANT, S.NOM, S.PRENOM, G.PAYS  from DANJOUV.LESPARTICIPATIONS P "
            + "join DANJOUV.LESSPORTIFS S  on (P.IDPARTICIPANT=S.IDSPORTIF) "
            + "join DANJOUV.LESPARTICIPANTS G on (P.IDPARTICIPANT=G.IDPARTICIPANT) "
            + "WHERE IDEPREUVE=%d";

    public static CachedRowSet getListeParticipantsAEpreuve(int idEpreuve) throws SQLException {
        return getConsulterParticipantsParEpreuveIndiv(lesParticipantsIndiv, idEpreuve);
    }

    private static CachedRowSet getConsulterParticipantsParEpreuveIndiv(String query, int idEpreuve) throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, idEpreuve));
        crs.execute();
        return crs;
    }
}
