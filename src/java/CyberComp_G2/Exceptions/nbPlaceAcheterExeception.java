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
        super("Probleme de place: plus de palce disponible");
    }

}
