/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.ConsulterEpreuve;

import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import static java.rmi.server.LogStream.log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Rencontre de |Sportif|s donnant lieu à un |Classement| 
 * @author vivi
 */
public class Epreuve {
    private int idEpreuve;
    private String nomEpreuve;
    private String nomDiscipline;
    private String dateDebut;
    private String dateFin;
    private String urlVideo;
    private double tarif;
    private int nbDePlace;
    private String categorie;
    private int nbPlaceAcheter;     
/**
 * contsurteur d'epreuve selon la contrainte CI_3
 * @param idEpreuve
 * @param nomEpreuve
 * @param nomDiscipline
 * @param dateDebut
 * @param dateFin
 * @param urlVideo
 * @param tarif
 * @param nbDePlace
 * @param categorie
 * @param nbPlaceAcheter
 * @throws CategorieException
 * @throws nbPlaceAcheterExeception 
 */
    public Epreuve(int idEpreuve ,String nomEpreuve,String nomDiscipline, String dateDebut, String dateFin, String urlVideo, double tarif, int nbDePlace, String categorie, int nbPlaceAcheter) throws CategorieException, nbPlaceAcheterExeception {
        this.idEpreuve = idEpreuve;
        this.nomDiscipline =nomDiscipline;
        this.nomEpreuve = nomEpreuve;
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        this.urlVideo = urlVideo;
        setTarif(tarif);
        setNbDePlace(nbDePlace);
        setCategorie(categorie);
        setNbPlaceAcheter(nbPlaceAcheter);
    }

    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public String getDateDebut() { 
        return dateDebut;
    }

    public String getDateFin() {
       return dateFin;
    }

    public String getNomDiscipline() {
        return nomDiscipline;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public double getTarif() {
        return tarif;
    }

    public void setDateDebut(String dateDebut){
       this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin){
        this.dateFin = dateFin; 
    }

    public int getNbDePlace() {
        return nbDePlace;
    }

    public int getIdEpreuve() {
        return idEpreuve;
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
