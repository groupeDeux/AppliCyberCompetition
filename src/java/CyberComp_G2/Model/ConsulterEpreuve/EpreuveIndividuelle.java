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
 *|Epreuve| dont les |Participant|s sont des |Sportif|s
 * @author vivi
 */
public class EpreuveIndividuelle  extends Epreuve{
    public EpreuveIndividuelle(String nomEpreuve, Date dateDebut, Date dateFin, String urlVideo, double tarif, int nbDePlace, String categorie, int nbPlaceAcheter) throws CategorieException, nbPlaceAcheterExeception {
        super(nomEpreuve, dateDebut, dateFin, urlVideo, tarif, nbDePlace, categorie, nbPlaceAcheter);
    }
    
}
