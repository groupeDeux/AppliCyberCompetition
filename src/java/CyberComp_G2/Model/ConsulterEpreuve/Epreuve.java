/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.ConsulterEpreuve;

import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import java.util.Date;

/**
 *
 * @author vivi
 */
public class Epreuve {
    private String nomEpreuve;
    private Date dateDebut;
    private Date dateFin;
    private String urlVideo;
    private double tarif;
    private int nbDePlace;
    private String categorie;
    private int nbPlaceAcheter;     

    public Epreuve(String nomEpreuve, Date dateDebut, Date dateFin, String urlVideo, double tarif, int nbDePlace, String categorie, int nbPlaceAcheter) throws CategorieException, nbPlaceAcheterExeception {
        this.nomEpreuve = nomEpreuve;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.urlVideo = urlVideo;
        setTarif(tarif);
        setNbDePlace(nbDePlace);
        setCategorie(categorie);
        setNbPlaceAcheter(nbPlaceAcheter);
    }

    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public double getTarif() {
        return tarif;
    }

    public int getNbDePlace() {
        return nbDePlace;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNbPlaceAcheter() {
        return nbPlaceAcheter;
    }
    
    /**
     * Verifie que tarif >= 0 sinon tarif =0
     * @param tarif 
     */
    public void setTarif(double tarif) {
        if (tarif>=0){
            this.tarif = tarif;
        }else{
            this.tarif= 0;
        }
    }
    /**
     * Verifie nbDePlace >=0 sinon nbPlace =0
     * @param nbDePlace 
     */
    public void setNbDePlace(int nbDePlace) {
        if (nbDePlace >= 0){
            this.nbDePlace = nbDePlace;
        }else{
            this.nbDePlace =0;        
        }
    }
    /**
     * Controle la categorie de l'epreve
     * @param categorie
     * @throws CategorieException 
     */
    public void setCategorie(String categorie) throws CategorieException {
        switch (categorie){
            case "masculin" :
               break;
            case "feminin" :
                break;
            case "mixte":
                break;
            default :
                throw new CategorieException(categorie);
        }
        this.categorie = categorie;
        
    }

    /**
     * Controle le nbDePlaceAcheter
     * @param nbPlaceAcheter
     * @throws nbPlaceAcheterExeception 
     */
    public void setNbPlaceAcheter(int nbPlaceAcheter) throws nbPlaceAcheterExeception {
        if (nbPlaceAcheter <= nbDePlace){
            this.nbPlaceAcheter = nbPlaceAcheter;
        }else{
            throw new nbPlaceAcheterExeception(nbPlaceAcheter,nbDePlace);
        }
            
    }
 
}
