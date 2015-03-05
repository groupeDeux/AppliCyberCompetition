/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Groupe de |Sportif|s d'un même |Pays| participant à une |Epreuve|
 * @author magourar
 */
public class Equipe {
    //--------- Attributs ------------------------------------------------------
    /*
     l'identifiant unique d'une equipe  dans notre BD sera le meme que Nequipe   de la  BD initiale
     */

    private int idEquipe;
    /*
     nom de l'équipe que nous avons  attribué pour chaque Nequipe 
     */
    private String nomEquipe;

    /*
     la categorie de l'équipe : féminin/masculin/mixte
     */
    private String categorie;

    /*
     calcul du nombre des membres de chaque équipe à partir d'une requete SQL viewEquipe
     */
    private int nbMembre;

    //---------Constructeur---------------------------------------------------
    public Equipe(int idEquipe, String nomEquipe, String categorie) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.categorie = categorie;
    }

  //---------Méthodes---------------------------------------------------
    /*
     getteurs
     */
    public int getIdEquipe() {
        return idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNbMembre() {
        return nbMembre;
    }

    /*
     setteur 
     */
    public void setnomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setcategorie(String categorie) {
        this.categorie = categorie;
    }
/*
    verifier si le nombre d'équipe est supérieur à 2  sinon lancer une exception
    */

    public void setNbMembre(int nbMembre) throws NbMembreIncorrectException {
        if (nbMembre < 2) {
            throw new NbMembreIncorrectException(); 
        }else{
            this.nbMembre = nbMembre;
        }
    }

}
