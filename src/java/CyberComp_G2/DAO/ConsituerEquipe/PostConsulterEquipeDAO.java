/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.ConsituerEquipe;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author oprisora
 */
public class PostConsulterEquipeDAO {

    public static final String lesParticipants = "INSERT * INTO LesParticipants values %";
    
    public static CachedRowSet postParticipants(int idParticipant) throws SQLException{
        return postConsulterEquipe(lesParticipants, idParticipant);
}
    
   
    private static CachedRowSet postConsulterEquipe(String query, String selecteur) 
            throws SQLException{
        CachedRowSet crs = new CachedRowSetImpl();  
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, selecteur));
        crs.execute();
        return crs; 
    }
    
    private static CachedRowSet postConsulterEquipe(String query, int selecteur) 
            throws SQLException{
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, selecteur));
        crs.execute();
        return crs;
    }
}

