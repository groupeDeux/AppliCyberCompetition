
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Groupe des |Participant|s d'un même |Pays|
 * @author magourar
 */
public class Delegation {
    
    //--------- Attributs ------------------------------------------------------

    //nom du pays de la délégation 

    private String pays;
    private ArrayList<Participant> lesParticipants;
    
    //--------- Méthodes ------------------------------------------------------

    public String getPays() {
        return pays;
    }

    public ArrayList<Participant> getLesParticipants() {
        return lesParticipants;
    }
}

