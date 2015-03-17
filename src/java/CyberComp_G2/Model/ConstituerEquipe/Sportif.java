package CyberComp_G2.Model.ConstituerEquipe;


import CyberComp_G2.Exceptions.CategorieException;
import java.sql.Date;


/**
 *|Personne| participant à une |Epreuve|
 * @author magourar
 */
public class Sportif extends Participant{

//--------- Attributs ------------------------------------------------------

    //le prenom du sportif
    private String prenom;

     //le nom d'un sportif 
    private  String nom;
    
    //la date de naissance d'un sportif 
    private  String dateNaissance;
    
    //le genre d'un sportif féminin ou masculin 
    private  String genre;

    //description de l'handicap si  c'est le cas 
    private  String descriptionHandicap;
    

   // ------------------------- Constructeur ---------------------------------
    /**
     * constructeur de sportif
     *
     * @param idSportif
     * @param pays
     * @param prenom
     * @param nom
     * @param dateNaissance
     * @param genre
     * @param descriptionHandicap
     */
    public Sportif(int idSportif,String pays, String prenom, String nom, String dateNaissance, String genre, String descriptionHandicap) throws CategorieException {
        super(idSportif,pays);
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        setGenre(genre);
        this.descriptionHandicap = descriptionHandicap;
    }

    public Sportif(int idSportif, String pays,String prenom, String nom, String dateNaissance, String genre) throws CategorieException {
        this(idSportif,pays,prenom,nom,dateNaissance,genre,null);
    }
    
   

    // ------------------------- Méthodes  --------------------------------
    public int getIdSportif(){
        return this.getIdParticipant();
    }
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getDateNaissance() {
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
    public void  setDateNaissance(String dateNaissance ) {
    this.dateNaissance= dateNaissance ;
    }
/*
   modifier le genre  d'un sportif 
    */
    public void   setGenre(String  genre ) throws CategorieException {
        switch (genre){
            case "feminin":
                this. genre=  genre;
                break;
            case "masculin":
                this.genre =genre;
                break;
            default:
                throw new CategorieException(genre);       
        }
    }
/*
    modifier description Handicap d'un sportif handicapé 
    */
    public void   setDescriptionHandicap(String descriptionHandicap) {
       this.descriptionHandicap=  descriptionHandicap;
    }
}
