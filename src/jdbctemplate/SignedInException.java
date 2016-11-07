/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctemplate;

/**
 *
 * @author albhja15
 */
public class SignedInException extends Exception {

    /**
     * Creates a new instance of <code>SignedInException</code> without detail
     * message.
     */
    public SignedInException() {
    }

    /**
     * Constructs an instance of <code>SignedInException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SignedInException(String msg) {
        super(msg);
    }
}
