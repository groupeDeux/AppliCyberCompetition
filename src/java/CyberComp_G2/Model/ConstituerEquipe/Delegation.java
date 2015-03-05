package CyberComp_G2.Model.ConstituerEquipe;


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
    
//--------- Constructeurs --------------------------------------------------

    public Delegation(String pays) {
        this.pays = pays;
        lesParticipants = new ArrayList();
    }

//--------- Méthodes ------------------------------------------------------
    public String getPays() {
        return pays;
    }

    public ArrayList<Participant> getLesParticipants() {
        return lesParticipants;
    }
    
    /**
     * Ajoute le participant a l'epreuve si idparticipant n'est pas deja present et si different de null
     * @param participantAAjouter 
     */
    public void  addParticipant (Participant participantAAjouter){
        if (participantAAjouter != null){
            for (Participant participant :lesParticipants){
                if (participant.getIdParticipant()==participantAAjouter.getIdParticipant()){
                    return;
                }                
            }
            lesParticipants.add(participantAAjouter);
        }
    }
    /**
     * Supprimme le particpant avec l'id idParticiapant si il existe
     * @param idParticipant 
     */
    public void delParticipant (int idParticipant){
        for (Participant participant :lesParticipants){
            if (participant.getIdParticipant()==idParticipant){
                    lesParticipants.remove(participant);
                }                
        }
    }
    
    public int getNbParticipant(){
       return lesParticipants.size();
    }
}

