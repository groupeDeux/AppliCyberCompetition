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
    
    public static final String lesEquipesDUneDelegation = 
            "SELECT * FROM LesEquipes E JOIN LesParticipants P on (E.idEquipe=P.idParticipant) WHERE pays ='%s'";
    
    public static CachedRowSet getEquipesDUneDelegation(String pays) 
            throws SQLException{
        return getConsulterEquipe(lesEquipesDUneDelegation, pays);
    }
    
    public static final String lesSportifsDUneEquipe = 
            "SELECT * FROM LesSportifs S JOIN LesEquipes E ON (S.idSportif=E.idEquipe) WHERE idSportif = %d";
    
    /**
     *
     * @param idEquipe
     * @return
     * @throws SQLException
     */
    
    public static CachedRowSet getSportifsDUneEquipe(int idEquipe) 
            throws SQLException{
        return getConsulterEquipe(lesSportifsDUneEquipe, idEquipe);
    }
    
    public static final String lesSportifsDUneDelegation = 
            "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant) WHERE pays='%s'";
    
    /**
     *
     * @param pays
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getSportifsDUneDelegation(String pays) throws SQLException{
        return getConsulterEquipe(lesSportifsDUneDelegation, pays);
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