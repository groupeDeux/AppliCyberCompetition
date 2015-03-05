/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesjava;

/**
 * @author agathe
 */

/** Represente  batiment physique des chambres attribuees aux sportifs
 */
public class Batiment {

    
    //--------- Attributs ------------------------------------------------------
    /**
     * le nom du batiment
     */
    String nomBatiment;
    
    /**
     * le numero de rue pour definir l adresse du batiment
     */
    int numRue;
    
    /**
     * la rue pour definir l adresse du batiment
     */
    String rue;
    
    /**
     * la ville pour definir l adresse du batiment
     */
    String ville;

    //--------- Constructeurs --------------------------------------------------
    /**
     * Construit un batiment avec une adresse: numRue, rue, ville
     *
     * @param numRue
     * @param rue
     * @param ville
     */
    public Batiment(String nomBatiment,int numRue, String rue, String ville) {
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
        this.nomBatiment=nomBatiment;

    }

}
