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
public class GenreMenbreEquipeException extends Exception {

    /**
     * Creates a new instance of <code>GenreMenbreEquipeException</code> without
     * detail message.
     */
    public GenreMenbreEquipeException() {
    }

    /**
     * Constructs an instance of <code>GenreMenbreEquipeException</code> with
     * the specified detail message.
     *
     * @param categorie
     * @param genre
     */
    public GenreMenbreEquipeException(String categorie,String genre) {
        super("genre du membre ("+genre +") Correspont pas a la categorie de l'equipe (" + categorie +")");
    }
}
