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
public class nbPersonneFixeExcepetion extends Exception {

    /**
     * Creates a new instance of <code>nbPersonneFixeExcepetion</code> without
     * detail message.
     */
    public nbPersonneFixeExcepetion() {
    }

    /**
     * Constructs an instance of <code>nbPersonneFixeExcepetion</code> with the
     * specified detail message.
     *
     * @param nbPersonneFixe
     */
    public nbPersonneFixeExcepetion(int nbPersonneFixe) {
        super("Nombre de Personne fixé incoherent, nbPersonneFixe : " +nbPersonneFixe);
    }
}
