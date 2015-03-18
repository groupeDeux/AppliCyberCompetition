/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.Panier;

import static CyberComp_G2.DAO.ConsituerEquipe.ModifierEquipeDAO.insertParticipant;
import CyberComp_G2.Model.ConstituerEquipe.Equipe;
import CyberComp_G2.Model.Utilisateur.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;

/**
 *
 * @author danjouv
 */
public  class SetPanierDAO {
    private static final String addUtilisateur = "insert into LESCOMPTES "
            + "values('%s','%s','%s',0,'%s','%s','%s','%s')";
    
    private static final String getMaxIdTransactions ="select max(IDTRANSACTION) from LESTRANSACTIONS";
    
    private static final String addTransaction ="insert into LESTRANSACTIONS "
            + "values (%d,'%s',to_date('%s','yyyy/mm/dd'))";
    
    private static  final  String getMaxIdTicket="select max(IDTICKET) from LESTICKETS";
    
     private static final String addTicket ="insert into LESTICKETS values (%d,%d,%d)";
     
     private static final String addTicketVideo ="insert into LESTICKETSVIDEO values (%d,'%s')";
     
     private static final String addBillet ="insert into LESBILLETS values (%d)";
     
     
    
    public static void addUtilisateur(Connection conn ,Utilisateur utilisateur) throws SQLException {
        Statement stmt = conn.createStatement();
        String IdUtilisateur =  utilisateur.getNom() + utilisateur.getPrenom() ;
        stmt.executeUpdate(String.format(addUtilisateur,IdUtilisateur,utilisateur.getNom(),
                utilisateur.getPrenom(),utilisateur.getNumRue(),utilisateur.getRue(),utilisateur.getVille(),
                utilisateur.getNumTelephone(),utilisateur.getMail()));
    }
                    
    public static void addTicket(Connection conn ,int idTicket,int idTransaction,int idEpreuve) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addTicket,idTicket,idTransaction,idEpreuve));
    }
    
    public static void addTicketVideo(Connection conn ,int idTicket) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addTicketVideo,idTicket,"code"+idTicket));
    }
    public static void addBillet(Connection conn ,int idTicket) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addBillet,idTicket));
    }
    
    
    public static int getNewIDTransaction(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(getMaxIdTransactions);
        int newID =0;
        while(rs.next()){
            newID = rs.getInt(1);
        }
        newID++;
        return newID;
    }
    
     public static int getNewIDTicket(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(getMaxIdTicket);
        int newID =0;
        while(rs.next()){
            newID = rs.getInt(1);
        }
        newID++;
        return newID;
    }
    
     public static void addTransaction(Connection conn,int idTransaction,String idUtilisateur) throws SQLException {
         Statement stmt = conn.createStatement();
         String dateCourante =  new SimpleDateFormat("yyyy/MM/dd").format(new Date());
         stmt.executeUpdate(String.format(addTransaction,idTransaction,idUtilisateur,dateCourante));
     }
    
}


