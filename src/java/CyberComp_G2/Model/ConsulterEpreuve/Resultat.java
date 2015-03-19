/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Model.ConsulterEpreuve;

import CyberComp_G2.Model.ConstituerEquipe.Participant;

/**
 *
 * @author fureta
 */
public class Resultat {
    Participant or;
    Participant argent;
    Participant bronze;

    public Resultat(Participant or, Participant argent, Participant bronze) {
        this.or = or;
        this.argent = argent;
        this.bronze = bronze;
    }

    public Participant getOr() {
        return or;
    }

    public Participant getArgent() {
        return argent;
    }

    public Participant getBronze() {
        return bronze;
    }
    
}
