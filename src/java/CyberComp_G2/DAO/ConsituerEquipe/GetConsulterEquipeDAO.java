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
    
    public static final String lesDelegations = 
            "SELECT * FROM viewDelegation order by pays";
    
    public static final String lesEquipesDUneDelegation = 
            "SELECT * FROM LesEquipes E JOIN LesParticipants P on (E.idEquipe=P.idParticipant) WHERE pays ='%s' order by idEquipe";
    
    public static final String lesSportifsDUneEquipe = 
            "SELECT * FROM LESCONSTITUTIONSEQUIPE join LESSPORTIFS USING (idSportif) where idEquipe =%d order by nom";
    
    public static final String lesSportifsDUneDelegation = 
            "SELECT * FROM LesSportifs S JOIN LesParticipants P on (S.idSportif=P.idParticipant) WHERE pays='%s' order by nom";
    
    /**
     *  retourne la liste des delegations
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getDelegations() throws SQLException{
        return getConsulterEquipe(lesDelegations,"");
    }
    /**
     * retroune la lsite des equipes d'un  delegation
     * @param pays
     * @return
     * @throws SQLException 
     */
    public static CachedRowSet getEquipesDUneDelegation(String pays) 
            throws SQLException{
        return getConsulterEquipe(lesEquipesDUneDelegation, pays);
    }
    
    /**
     * reroune la liste des sportif d'une equipe
     * @param idEquipe
     * @return
     * @throws SQLException
     */
    public static CachedRowSet getSportifsDUneEquipe(int idEquipe) 
            throws SQLException{
        return getConsulterEquipe(lesSportifsDUneEquipe, idEquipe);
    }
    
    
    
    /**
     * retroune la lsite des Sportif d'une delegation
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