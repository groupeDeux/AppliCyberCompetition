/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CyberComp_G2.Exceptions;

/**
 *
 * @author danjouv
 */
public class EquipeNonModifiable extends Exception {

    /**
     * Creates a new instance of <code>EquipeNonModifiable</code> without detail
     * message.
     */
    public EquipeNonModifiable() {
    }

    /**
     * Constructs an instance of <code>EquipeNonModifiable</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EquipeNonModifiable(String msg) {
        super(msg);
    }
}
