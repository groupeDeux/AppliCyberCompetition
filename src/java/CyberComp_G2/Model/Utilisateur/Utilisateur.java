/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.Utilisateur;

/**
 *
 * @author Gato
 */
public class Utilisateur {

    private String nom;
    private String prenom;
    private String adresseMail;
    private String numeroTelephone;
    private int numRue;
    private String rue;
    private String ville;
    private String typeCarte;
    private boolean panierValider;
    private boolean infoValider;
    private boolean paiementValider;
    private boolean commandeTerminer;

    public Utilisateur(String nom, String prenom, String adresseMail, String numeroTelephone, int numRue, String rue, String ville, String typeCarte, boolean panierValider, boolean infoValider, boolean paiementValider, boolean commandeTerminer) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.numeroTelephone = numeroTelephone;
        this.numRue = numRue;
        this.rue = rue;
        this.ville = ville;
        this.typeCarte = typeCarte;
        this.panierValider = panierValider;
        this.infoValider = infoValider;
        this.paiementValider = paiementValider;
        this.commandeTerminer = commandeTerminer;
    }



    public Utilisateur() {
        this("","","", "", 0, "", "", "", false, false, false, false);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public boolean isPanierValider() {
        return panierValider;
    }

    public void setPanierValider(boolean panierValider) {
        this.panierValider = panierValider;
    }

    public boolean isInfoValider() {
        return infoValider;
    }

    public void setInfoValider(boolean infoValider) {
        this.infoValider = infoValider;
    }

    public boolean isPaiementValider() {
        return paiementValider;
    }

    public void setPaiementValider(boolean paiementValider) {
        this.paiementValider = paiementValider;
    }

    public boolean isCommandeTerminer() {
        return commandeTerminer;
    }

    public void setCommandeTerminer(boolean commandeTerminer) {
        this.commandeTerminer = commandeTerminer;
    }

    
}
