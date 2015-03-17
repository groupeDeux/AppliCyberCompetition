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

    private ArrayList<Integer> nombreDElements;
    private ArrayList<String> listeAuPanier;
    private ArrayList<Epreuve> lesEpreuvesAuPanier;

    public Panier() {
        nombreDElements = new ArrayList();
        listeAuPanier = new ArrayList();
        lesEpreuvesAuPanier = new ArrayList();
    }

    /* Retourne une ArrayList contenant la quantitee contenue de chaque billets dans le panier */
    public ArrayList<Integer> getNombreDeBillet() {
        return nombreDElements;
    }
    /* Fonction la quantitee commandé pour un certain billet */
    public void modifierNombreDeBillet(int numeroDuBillet, int nouvelleQuantitee){
        this.nombreDElements.set(numeroDuBillet,nouvelleQuantitee);
    }
    
    /* Fonction permettant d'ajouter un billet au panier*/
    public void ajouterUnBillet(Epreuve epreuveAAjouter, String typeDeBillet, int nombre) {
        if (nombre != 0 && epreuveAAjouter != null) {
            lesEpreuvesAuPanier.add(epreuveAAjouter);
            listeAuPanier.add(typeDeBillet);
            nombreDElements.add(nombre);
        }
        //Il faudrait lancer un exception ici disant qu'il n'y a pas le bon nombre de billet ou autre 
    }
    
    /* 
        Fonction permettant de supprimer un billet du panier 
        Il faut pouvoir retourner une exception indiquant que le nombre de billets est < 0
    */
    public void supprimerUnBillet(int nombre){
        this.lesEpreuvesAuPanier.remove(nombre);
        this.listeAuPanier.remove(nombre);
        this.nombreDElements.remove(nombre);
    }
    
    /* Fonction permettant de supprimer tous les billets du panier */
    public void supprimerLePanierComplet(){
        this.listeAuPanier.clear();
        this.lesEpreuvesAuPanier.clear();
        this.nombreDElements.clear();
    }
    
    /* Fonction affichant le montant total des billets dans le panier */
    public double montantTotal() {
        double montantTotal = 0;
        for(int i = 0; i<lesEpreuvesAuPanier.size(); i++){
            int nombreDeBillets = nombreDElements.get(i);
            double tarifDuBillet = lesEpreuvesAuPanier.get(i).getTarif();
            montantTotal += nombreDeBillets * tarifDuBillet;
        }
        return montantTotal;
    }
    
    public ArrayList<Epreuve> getLesEpreuvesAuPanier(){
        return lesEpreuvesAuPanier;
    }
    
    public ArrayList<String> getListeAuPanier(){
        return listeAuPanier;
    }
    
}
