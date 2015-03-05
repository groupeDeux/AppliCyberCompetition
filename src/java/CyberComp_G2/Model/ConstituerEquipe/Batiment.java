/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.ConstituerEquipe;

import java.util.ArrayList;

/**
 * Structure d'un |VillageSportif| comprenant les |Chambre|s des |Sportif|s
 * @author agathe
 */
public class Batiment {

    
//--------- Attributs ------------------------------------------------------
    // le nom du batiment
    private String nomBatiment;
    
    //le numero de rue pour definir l adresse du batiment
    private int numRue;
    
    //la rue pour definir l adresse du batiment
    private String rue;
    
    // la ville pour definir l adresse du batiment
    private String ville;
    
    private ArrayList<Chambre> lesChambres;

//--------- Constructeurs --------------------------------------------------
    /**
     * Construit un batiment avec une adresse: numRue, rue, ville
     * @param nomBatiment
     * @param numRue
     * @param rue
     * @param ville
     */
    public Batiment(String nomBatiment,int numRue, String rue, String ville) {
        this.lesChambres = new ArrayList();
        this.nomBatiment=nomBatiment;
        this.numRue=numRue;
        this.rue=rue;
        this.ville=ville;
    }

    /**
     * Construit un batiment sans adresse definie
     * @param nomBatiment
     */
    public Batiment(String nomBatiment) {
        this(nomBatiment,0,null,null);
    }

// ------------------------- Methodes ---------------------------------
    /**
     * Ajout la chambreAAjouter  au batiment si not null
     * La chambre n'est pas ajouter si sont numChambre est deja present
     * @param chambreAAjouter 
     */
    public void addChambre(Chambre chambreAAjouter){
        if(chambreAAjouter !=null){
            for (Chambre chambre : lesChambres) {
               if (chambre.getNumChambre()==chambreAAjouter.getNumChambre()){
                 return;  
               }
            }
            lesChambres.add(chambreAAjouter);
        }
    }
    /**
     * Supprime la chambre de numero numChambre si elle existe
     * @param numChambre 
     */
    public void delChambre(int numChambre){
        for (Chambre chambre : lesChambres) {
            if(chambre.getNumChambre() ==numChambre){
                lesChambres.remove(chambre);
            }
        }
    }

    /**
     * Renvoie la listes des numeros de chambres du batiment
     * @return 
     */
    public ArrayList<Integer> getLesNumChambres(){
        ArrayList<Integer> lesNumChambres= new ArrayList();
        for (Chambre chambre : lesChambres) {
            lesNumChambres.add(chambre.getNumChambre());
        }
        return lesNumChambres;
    }
    
    /**
     * Return la chambre numChambre du batiment si elle existe
     * sinon null
     * @param numChambre
     * @return 
     */
    public Chambre getChambre(int numChambre){
        for (Chambre chambre : lesChambres) {
               if (chambre.getNumChambre()==numChambre){
                 return chambre;
               }
        }
        return null;
    }
    
   

}
