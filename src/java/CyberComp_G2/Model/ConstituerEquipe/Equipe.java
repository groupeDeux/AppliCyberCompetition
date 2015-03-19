package CyberComp_G2.Model.ConstituerEquipe;

import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.GenreMenbreEquipeException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Groupe de |Sportif|s d'un même |Pays| participant à une |Epreuve|
 * @author magourar
 */
public class Equipe extends Participant{
//--------- Attributs ------------------------------------------------------

    //nom de l'équipe que nous avons  attribué pour chaque Nequipe 
    private String nomEquipe;

    //la categorie de l'équipe : féminin/masculin/mixte
    private String categorie;
    
    private ArrayList<Sportif> lesMembres;
    
    private int nbDeSportif =2 ;

    //---------Constructeur---------------------------------------------------
    
    public Equipe(int idEquipe,String pays, String nomEquipe, String categorie,int nbDeSportif) throws CategorieException {
        super(idEquipe,pays);
        this.nomEquipe = nomEquipe;
        setcategorie(categorie);
        lesMembres = new ArrayList<>();
        this.nbDeSportif=nbDeSportif;
    }

    public Equipe( int idEquipe, String pays,String categorie,int nbDeSportif) throws CategorieException {
        this(idEquipe,pays,null,categorie,nbDeSportif);
    }
    
    public int getNbDeSportif() {
        return nbDeSportif;
    }

    //---------Méthodes---------------------------------------------------
    public void setNbDeSportif(int nbDeSportif) {
        if (nbDeSportif<2){
           this.nbDeSportif =2; 
        }
        this.nbDeSportif = nbDeSportif;
    }

    public int getIdEquipe() {
        return this.getIdParticipant();
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNbMembre() {
        return lesMembres.size();
    }

    public void setnomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setcategorie(String categorie) throws CategorieException {
        switch(categorie){
            case "feminin":
                this.categorie = categorie;
                break;
            case "masculin":
                this.categorie = categorie;
                break;
            case "mixte":
                this.categorie = categorie;
                break;
            default: 
                throw new CategorieException(categorie);
        }
    }
    
     /**
     * Ajoute a un sportif a une equipe
     * on parcour la liste des membre de cette équipe si il est present on fait rien 
     * si non on verifie que son genre correspond à la catégorie de l'équipe et on l'ajoute 
     * @param sportifAAjouter 
     * @throws CyberComp_G2.Exceptions.GenreMenbreEquipeException 
     */
    public void addMembre(Sportif sportifAAjouter) throws GenreMenbreEquipeException{
        if(sportifAAjouter !=null){
            for (Sportif sportif : lesMembres) {
               if (sportif.getIdSportif()==sportifAAjouter.getIdSportif()){
                 return;  
               }
            }
            if(!"mixte".equals(categorie)){
                if(categorie.equals(sportifAAjouter.getGenre())){
                    lesMembres.add(sportifAAjouter);
                }else{
                    throw new GenreMenbreEquipeException(categorie,sportifAAjouter.getGenre());
                }        
            }else{
                lesMembres.add(sportifAAjouter);
            }
            
        }
    }

    public ArrayList<Sportif> getLesMembres() {
        return lesMembres;
    }
    
    
    /**
     * Supprimer un  Sportif membre d'une équipe  d'identifiant idSportif 
     * @param idSportif 
     */
    public void delMembre(int idSportif){
        int i;
        for (i=0;i<lesMembres.size();i++) {
            if (lesMembres.get(i).getIdSportif()==idSportif){
               lesMembres.remove(i);
            }
        }
    }

    @Override
    public String asHTML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
