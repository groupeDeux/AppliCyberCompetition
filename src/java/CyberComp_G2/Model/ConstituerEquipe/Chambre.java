/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.ConstituerEquipe;

import java.util.ArrayList;

/**
 * Lieu où sont logés les |Sportif|s dans un |Batiment|
 * @author agathe
 */
public class Chambre{

    
//--------- Attributs ------------------------------------------------------
    // le numero de la chambre
    private int numChambre;
    
    //la capacite de la chambre soit le nombre de sportifs maximum qu elle peut recevoir
    private int capacite;
    
    private String genre;
    
    private String  pays;
      
    private ArrayList<Sportif> lesSportifs;


//--------- Constructeurs --------------------------------------------------
    /**
     * Crée une chambre vide  avec numero de chambre et une capacite
     * @param numChambre
     * @param capacite
     */
    public Chambre(int numChambre, int capacite) {
        this.numChambre = numChambre;
        this.capacite = capacite;
        lesSportifs = new ArrayList();
    }

// ------------------------- Methodes ---------------------------------
    public int getNumChambre() {
        return numChambre;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getGenre() {
        return genre;
    }

    public String getPays() {
        return pays;
    }

    public int getNbPlacesUtilisée() {
        return lesSportifs.size();
    }

    public ArrayList<Sportif> getLesSportifs() {
        return lesSportifs;
    }
    
    /**
     * Ajoute a un sportif a la chambre si non present et different de null
     * @param sportifAAjouter 
     */
    public void addSportif(Sportif sportifAAjouter){
        if(sportifAAjouter !=null){
            for (Sportif sportif : lesSportifs) {
               if (sportif.getIdSportif()==sportifAAjouter.getIdSportif()){
                 return;  
               }
            }
            lesSportifs.add(sportifAAjouter);
        }
    }
    /**
     * Suprime le Sportif d'identifiant idSportif si il existe
     * @param idSportif 
     */
    public void delSportif (int idSportif){
        for (Sportif sportif : lesSportifs) {
            if (sportif.getIdSportif()==idSportif){
                lesSportifs.remove(sportif);
            }
        }
    }
}

