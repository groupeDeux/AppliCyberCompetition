/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.Panier;

import CyberComp_G2.Exceptions.PanierException;
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

    /* Constructeur par défaut */
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

    public void modifierNombreDeBillet(int numeroDuBillet, int nouvelleQuantitee) throws PanierException {
        if (nouvelleQuantitee <= 0) {
            throw new PanierException(" methode modifierNombreDeBillet, quantitee <= 0");
        } else if (nombreDElements.size() < numeroDuBillet) {
            throw new PanierException(" methode modifierNombreDeBillet, taille du panier < numero du billet envoyé");
        } else {
            this.nombreDElements.set(numeroDuBillet, nouvelleQuantitee);
        }
    }

    /* Fonction permettant d'ajouter un billet au panier*/
    public void ajouterUnBillet(Epreuve epreuveAAjouter, String typeDeBillet, int nombre) throws PanierException {
        if (nombre <= 0) {
            throw new PanierException(" methode ajouterUnBillet, nombre de billet incorrect");
        } else if (epreuveAAjouter == null) {
            throw new PanierException(" methode ajouterUnBillet, epreuve null");
        } else {
            lesEpreuvesAuPanier.add(epreuveAAjouter);
            listeAuPanier.add(typeDeBillet);
            nombreDElements.add(nombre);
        }
    }

    /* 
     Fonction permettant de supprimer un billet du panier 
     Il faut pouvoir retourner une exception indiquant que le nombre de billets est < 0
     */
    public void supprimerUnBillet(int nombre) throws PanierException {
        if (lesEpreuvesAuPanier.isEmpty()) {
            throw new PanierException(" methode supprimerUnBillet, le panier est vide");
        } else if (lesEpreuvesAuPanier.size() < nombre) {
            throw new PanierException(" methode supprimerUnBillet, taille du panier inférieur au nombre envoyé");
        } else {
            this.lesEpreuvesAuPanier.remove(nombre);
            this.listeAuPanier.remove(nombre);
            this.nombreDElements.remove(nombre);
        }
    }

    /* Fonction permettant de supprimer tous les billets du panier */
    public void supprimerLePanierComplet() throws PanierException {
        if (lesEpreuvesAuPanier.isEmpty()) {
            throw new PanierException(" methode lesEpreuvesAuPanier, le panier est deja vide");
        } else {
            this.listeAuPanier.clear();
            this.lesEpreuvesAuPanier.clear();
            this.nombreDElements.clear();
        }
    }

    /* Fonction affichant le montant total des billets dans le panier */
    public double montantTotal() {
        double montantTotal = 0;
        if (!lesEpreuvesAuPanier.isEmpty()) {
            for (int i = 0; i < lesEpreuvesAuPanier.size(); i++) {
                int nombreDeBillets = nombreDElements.get(i);
                double tarifDuBillet = lesEpreuvesAuPanier.get(i).getTarif();
                montantTotal += nombreDeBillets * tarifDuBillet;
            }
        }
        return montantTotal;

    }

    public ArrayList<Epreuve> getLesEpreuvesAuPanier() {
        return lesEpreuvesAuPanier;
    }

    public ArrayList<String> getListeAuPanier() {
        return listeAuPanier;
    }

}
