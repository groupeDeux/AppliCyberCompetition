/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import static CyberComp_G2.DAO.ConsulterEpreuve.GetConsulterEpreuveDAO.lesDisciplines;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Gato
 */
public class GetConsulterDisciplineDAO {

    public static final String lesDisciplines
            = "SELECT nomDiscipline FROM viewEpreuve GROUP BY (nomDiscipline)";

    public static CachedRowSet getDisciplines() throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(lesDisciplines));
        crs.execute();
        return crs;
    }
}