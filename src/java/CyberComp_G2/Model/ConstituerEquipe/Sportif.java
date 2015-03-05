
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author magourar
 *
 */
public class Sportif {

    //--------- Attributs ------------------------------------------------------
    /*
     l'identifiant unique du sportif dans notre BD sera le meme que NS  de la  BD initiale
     */
    private  int idSportif;
    /*
     le prenom du sportif
     */
    private  String prenom;
    /*
     le nom d'un sportif 
     */
    private  String nom;
    /*
     la date de naissance d'un sportif 
     */
    private  Date dateNaissance;
    /*
     le genre d'un sportif féminin ou masculin 
     */
    private  String genre;
    /*
     description de l'handicap si  c'est le cas 
     */
    private  String descriptionHandicap;
    

   // ------------------------- Constructeur ---------------------------------
    /**
     * constructeur de sportif
     *
     * @param idSportif
     * @param prenom
     * @param nom
     * @param dateNaissance
     * @param genre
     * @param descriptionHandicap
     */
    public Sportif(int idSportif, String prenom, String nom, Date dateNaissance, String genre, String descriptionHandicap) {
        this.idSportif = idSportif;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.descriptionHandicap = descriptionHandicap;
    }

    // ------------------------- Méthodes  --------------------------------
     // -------------------------getteurs--------------------------------
    public int getIdSportif() {
        return idSportif;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescriptionHandicap() {
        return descriptionHandicap;
    }

 // ------------------------- setteur   --------------------------------
/*
    modifier le prenom d'un sportif 
    */
    public void  setPrenom(String prenom) {
        this.prenom = prenom;
    }
/*
    modifier le nom d'un sportif 
    */
    public void  setNom(String nom ) {
        this.nom = nom ;
    }
/*
    modifier la date de Naissance d'un sportif 
    */
    public void  setDateNaissance(Date dateNaissance ) {
    this.dateNaissance= dateNaissance ;
    }
/*
   modifier le genre  d'un sportif 
    */
    public void   setGenre(String  genre ) {
     this. genre=  genre;
    }
/*
    modifier description Handicap d'un sportif handicapé 
    */
    public void   setDescriptionHandicap(String descriptionHandicap) {
       this.descriptionHandicap=  descriptionHandicap;
    }
}
