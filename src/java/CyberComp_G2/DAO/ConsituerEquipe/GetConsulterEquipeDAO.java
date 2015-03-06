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
    
    public static final String lesDelegations = "SELECT * FROM viewDelegation";
    
    public static CachedRowSet getDelegations() throws SQLException{
        return getConsulterEquipe(lesDelegations,"");
    }
    
    public static final String lesEquipes = "SELECT * FROM LesEquipes WHERE pays ='%s'";
    
    public static CachedRowSet getEquipes(String pays) throws SQLException{
        return getConsulterEquipe(lesEquipes, pays);
    }
    
    public static final String lesSportifsDUneEquipe = "SELECT * FROM LesSportifs WHERE idSportif = %d";
    
    /**
     *
     * @param idEquipe
     * @return
     * @throws SQLException
     */
    
    public static CachedRowSet getSportifsDUneEquipe(int idEquipe) throws SQLException{
        return getConsulterEquipe(lesSportifsDUneEquipe, idEquipe);
    }
    
    public static final String lesSportifs = "SELECT * FROM LesSportifs";
    
    /**
     *
     * @param idSportif
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getConsulterEquipe(int idSportif) throws SQLException{
        return getConsulterEquipe(lesSportifs, idSportif);
    } 
    
    private static CachedRowSet getConsulterEquipe(String query, String selecteur) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query,selecteur));
        crs.execute();
        return crs;
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