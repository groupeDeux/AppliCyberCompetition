/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.Panier;

import CyberComp_G2.Model.ConsulterEpreuve.Epreuve;
import java.util.ArrayList;

/**
 * Model de la classe panier
 *
 * @author Gato
 */
public class Panier {
    /*
     Définition des attributs de la classe Panier
     billet correspond au nombre de billet dans le panier
     ticketVideo correspond au nombre de tickets video dans le panier
     montantPanier correspond à la somme des prix des billets et tickets video
     dans le panier.
     */

    private int nombreDeBillets;
    private ArrayList<String> listeAuPanier;
    private ArrayList<Epreuve> lesEpreuvesAuPanier;

    public Panier() {
        this.nombreDeBillets = 0;
        this.listeAuPanier = new ArrayList();
        this.lesEpreuvesAuPanier = new ArrayList();
    }

    public int getNombreDeBillet() {
        return this.nombreDeBillets;
    }

    public void setNombreDeBillet(int nombre) {
        this.nombreDeBillets += nombre;
    }

    public void ajouterUnBillet(Epreuve epreuveAAjouter, String typeDeBillet, int nombre) {
        if (nombre != 0 && epreuveAAjouter != null) {
            this.lesEpreuvesAuPanier.add(epreuveAAjouter);
            this.listeAuPanier.add(typeDeBillet);
            this.nombreDeBillets += nombre;
        }
    }

    public int montantTotal() {
        int montantTotal = 0;
        for (Epreuve ep : lesEpreuvesAuPanier) {
            montantTotal += ep.getTarif();
        }
        return montantTotal;
    }
    
    public ArrayList<Epreuve> getLesEpreuvesAuPanier(){
        return lesEpreuvesAuPanier;
    }
    
}
