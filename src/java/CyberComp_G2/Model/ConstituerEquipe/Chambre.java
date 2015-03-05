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
public class Chambre{

    
    //--------- Attributs ------------------------------------------------------
    /**
     * le numero de la chambre
     */
    int numChambre;
    
    /**
     * la capacite de la chambre soit le nombre de sportifs maximum qu elle peut recevoir
     */
    int capacite;
    

    //--------- Constructeurs --------------------------------------------------
    /**
     * Construit un batiment avec numero de chambre et capacite
     * @param numChambre
     * @param capacite
     */
    public Chambre(int numChambre,int capacite) {
        this.numChambre=numChambre;
        this.capacite=capacite;
        
    }

    // ------------------------- Methodes ---------------------------------

    /* ATTRIBUT CALCULES = CALCULES PAR SQL
    = ATTRIBUTS DE LA CLASSE SYSTEMATIQUEMENT OU METHODES ??*/
}

