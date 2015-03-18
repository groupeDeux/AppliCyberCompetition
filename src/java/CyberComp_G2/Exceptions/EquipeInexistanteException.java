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
public class EquipeInexistanteException extends Exception {

    /**
     * Creates a new instance of <code>EquipeInexistanteException</code> without
     * detail message.
     */
    public EquipeInexistanteException() {
    }

    /**
     * Constructs an instance of <code>EquipeInexistanteException</code> with
     * the specified detail message.
     *
     * @param idEquipe
     */
    public EquipeInexistanteException(int idEquipe) {
        super("l'equipe : " + idEquipe +" est pas axcecible");
    }
}
