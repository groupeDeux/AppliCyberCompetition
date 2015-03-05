package CyberComp_G2.Model.ConstituerEquipe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *|Sportif| ou |Equipe| participant à une |Competition|
 * @author magourar
 */
public class Participant {
//--------- Attributs -----------------------------------------------------
      
    //nom du pays de la délégation 
    private  String pays;
    private int idParticipant;
    
//--------- Constructeurs --------------------------------------------------

    protected Participant(int idParticipant,String pays) {
        this.pays = pays;
        this.idParticipant = idParticipant;
    }
    
    
//--------- Méthodes ------------------------------------------------------
    public String getPays() {
        return pays;
    }

    public int getIdParticipant() {
        return idParticipant;
    }
    
}
