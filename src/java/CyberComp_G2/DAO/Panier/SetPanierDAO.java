/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.DAO.Panier;

import CyberComp_G2.Model.Utilisateur.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author danjouv
 */
public  class SetPanierDAO {
    private static final String addUtilisateur = "insert into LESCOMPTES "
            + "values('%s','%s','%s',%d,'%s','%s','%s','%s')";
    
    private static final String getMaxIdTransactions ="select max(IDTRANSACTION) from LESTRANSACTIONS";
    
    private static final String addTransaction ="insert into LESTRANSACTIONS "
            + "values (%d,'%s',to_date('%s','yyyy/mm/dd'))";
    
    private static  final  String getMaxIdTicket="select max(IDTICKET) from LESTICKETS";
    
     private static final String addTicket ="insert into LESTICKETS values (%d,%d,%d)";
     
     private static final String addTicketVideo ="insert into LESTICKETSVIDEO values (%d,'%s')";
     
     private static final String addBillet ="insert into LESBILLETS values (%d)";
     
     
    private static  final  String getMailDansCompte="select mail from LESComptes  WHERE mail='%s'";
     
     private static final String billetAchetable ="select * from VIEWEPREUVE "
             + "join LESTICKETS using(IDEpreuve) "
             + "where (NBDEPLACEACHETÉES >= NBDEPLACE and IDEpreuve= %d)";
     
    /**
     * Ajout un nupplet compte dans la base de BD
     * @param conn
     * @param utilisateur
     * @throws SQLException 
     */
    public static void addUtilisateur(Connection conn ,Utilisateur utilisateur) throws SQLException {
        Statement stmt = conn.createStatement();
        String IdUtilisateur = utilisateur.getMail() ;
        stmt.executeUpdate(String.format(addUtilisateur,IdUtilisateur,utilisateur.getNom(),
                utilisateur.getPrenom(),utilisateur.getNumRue(),utilisateur.getRue(),utilisateur.getVille(),
                utilisateur.getNumTelephone(),utilisateur.getMail()));
    }
     
    public static boolean  testTicket(Connection conn,int idEpreuve) throws SQLException{
        boolean ticketAchetable = true;
        Statement stmt = conn.createStatement();
        ResultSet rs= stmt.executeQuery(String.format(billetAchetable,idEpreuve));
        if(rs.next()){
             ticketAchetable  = false;
         }
        return ticketAchetable;
    }
    /**
     * Ajout un Nupplet Ticket dans la base de donnée
     * @param conn
     * @param idTicket
     * @param idTransaction
     * @param idEpreuve
     * @throws SQLException 
     */
    public static void addTicket(Connection conn ,int idTicket,int idTransaction,int idEpreuve) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addTicket,idTicket,idTransaction,idEpreuve));
    }
    
    /**
     * Ajout un ticket vidéo dans la base de donnée
     * @param conn
     * @param idTicket
     * @throws SQLException 
     */
    public static void addTicketVideo(Connection conn ,int idTicket) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addTicketVideo,idTicket,"code"+idTicket));
    }
    /**
     * ajout un billet dans la base de donnée
     * @param conn
     * @param idTicket
     * @throws SQLException 
     */
    public static void addBillet(Connection conn ,int idTicket) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format(addBillet,idTicket));
    }
    
    /**
     * Renvoie un id valide pour une future transaction
     * @param conn
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * renvoie un id valide pour un future Ticket
     * @param conn
     * @return
     * @throws SQLException 
     */
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
    
     /**
      * verifie sir le mail est present dans la BD 
      * true si present
      * @param conn
      * @param mail
      * @return
      * @throws SQLException 
      */
     public static boolean mailIsPresent(Connection conn ,String mail) throws SQLException{
          boolean isPresent = false;
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(String.format(getMailDansCompte,mail));
          if(rs.next()){
              isPresent = true;
          }
          return isPresent;
     }
     /**
      * Ajout une transaction dans la base de donnée
      * @param conn
      * @param idTransaction
      * @param idUtilisateur
      * @throws SQLException 
      */
     public static void addTransaction(Connection conn,int idTransaction,String idUtilisateur) throws SQLException {
         Statement stmt = conn.createStatement();
         String dateCourante =  new SimpleDateFormat("yyyy/MM/dd").format(new Date());
         stmt.executeUpdate(String.format(addTransaction,idTransaction,idUtilisateur,dateCourante));
     }
    
}


