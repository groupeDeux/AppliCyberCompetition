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

    public ArrayList<Integer> getNombreDeBillet() {
        return nombreDElements;
    }
    

    public void ajouterUnBillet(Epreuve epreuveAAjouter, String typeDeBillet, int nombre) {
        if (nombre != 0 && epreuveAAjouter != null) {
            lesEpreuvesAuPanier.add(epreuveAAjouter);
            listeAuPanier.add(typeDeBillet);
            nombreDElements.add(nombre);
        }
        //Il faudrait lancer un exception ici disant qu'il n'y a pas le bon nombre de billet ou autre 
    }
    
    public void supprimerUnBillet(int nombre){
        this.lesEpreuvesAuPanier.remove(nombre);
        this.listeAuPanier.remove(nombre);
        this.nombreDElements.remove(nombre);
    }
    
    public void supprimerLePanierComplet(){
        
        this.listeAuPanier.clear();
        this.lesEpreuvesAuPanier.clear();
        this.nombreDElements.clear();
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
    
    public ArrayList<String> getListeAuPanier(){
        return listeAuPanier;
    }
    
}
