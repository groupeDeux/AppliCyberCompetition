/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsituerEquipe;


import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi
 */
public class GetConsulterEquipeDAO {
    
    public static final String lesDelegations ="SELECT pays FROM viewDelegation";
    
    public static CachedRowSet getDelegations() throws SQLException{
        return getConsulterEquipe(lesDelegations,0);
    }
    
    private static CachedRowSet getConsulterEquipe(String query, int selecteur) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,selecteur));
        crs.execute();
        return crs;
    }
}
