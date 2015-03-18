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
public class TypeTicketNonCorrect extends Exception {

    /**
     * Creates a new instance of <code>TypeTicketNonCorrecte</code> without
     * detail message.
     */
    public TypeTicketNonCorrect() {
    }

    /**
     * Constructs an instance of <code>TypeTicketNonCorrecte</code> with the
     * specified detail message.
     *
     * @param typeTicket
     */
    public TypeTicketNonCorrect(String typeTicket) {
        super("le type de ticket n'est pas correct, type de ticket : "+typeTicket);
    }
}
