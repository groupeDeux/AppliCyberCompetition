/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Model.ConsulterEpreuve;

import CyberComp_G2.Exceptions.CategorieException;
import CyberComp_G2.Exceptions.nbPersonneFixeExcepetion;
import CyberComp_G2.Exceptions.nbPlaceAcheterExeception;
import java.text.ParseException;
import java.util.Date;

/**
 * |Epreuve| dont les |Participant|s sont des |Equipe|s
 * @author vivi
 */
public class EpreuveParEquipe extends Epreuve{
    private int  nbPersonneFixe ;
    public EpreuveParEquipe(int idEpreuve,String nomDiscipline,String nomEpreuve, String dateDebut, String dateFin, String urlVideo, double tarif, int nbDePlace, String categorie, int nbPlaceAcheter) throws CategorieException, nbPlaceAcheterExeception{
        super(idEpreuve,nomEpreuve,nomDiscipline, dateDebut, dateFin, urlVideo, tarif, nbDePlace, categorie, nbPlaceAcheter);
    }
    public EpreuveParEquipe(int idEpreuve,String nomDiscipline,String nomEpreuve, String dateDebut, String dateFin, String urlVideo, double tarif, int nbDePlace, String categorie, int nbPlaceAcheter, int nbPlaceFixe) throws CategorieException, nbPlaceAcheterExeception, nbPersonneFixeExcepetion {
        super(idEpreuve,nomEpreuve,nomDiscipline, dateDebut, dateFin, urlVideo, tarif, nbDePlace, categorie, nbPlaceAcheter);
        setNbPersonneFixe(nbPersonneFixe);
    }
    public int getNbPersonneFixe() {
        return nbPersonneFixe;
    }
    /**
     * Verifier que le nombre de personneFixé est supétieure a 2
     * @param nbPersonneFixe
     * @throws nbPersonneFixeExcepetion 
     */
    public void setNbPersonneFixe(int nbPersonneFixe) throws nbPersonneFixeExcepetion {
        if (nbPersonneFixe<2){
            throw new nbPersonneFixeExcepetion(nbPersonneFixe);
        }else{
            this.nbPersonneFixe =nbPersonneFixe;
        }
        
    }
    
    
}
