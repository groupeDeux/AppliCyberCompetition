/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.Exceptions;

/**
 *
 * @author Gato
 */
public class PanierException extends Exception {

    /**
     * Creates a new instance of <code>PanierException</code> without detail
     * message.
     */
    public PanierException() {
    }

    /**
     * Constructs an instance of <code>PanierException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PanierException(String msg) {
        super("Erreur dans la classe panier :" + msg);
    }
}
