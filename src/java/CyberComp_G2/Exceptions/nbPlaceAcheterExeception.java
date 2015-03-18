/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Exceptions;

/**
 *
 * @author vivi
 */
public class nbPlaceAcheterExeception extends Exception {

    /**
     * Creates a new instance of <code>nbPlaceAcheterExeception</code> without
     * detail message.
     */
    public nbPlaceAcheterExeception() {
    }

    /**
     * Constructs an instance of <code>nbPlaceAcheterExeception</code> with the
     * specified detail message.
     *
     * @param nbPlace
     * @param nbPlaceAcheter
     */
    public nbPlaceAcheterExeception(int nbPlace,int nbPlaceAcheter) {
        super("Probleme de place, place de l'épreuve :" +nbPlace + nbPlaceAcheter);
    }
}
