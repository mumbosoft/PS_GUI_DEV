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
public class SignedOutException extends Exception {

    /**
     * Creates a new instance of <code>SignedOutException</code> without detail
     * message.
     */
    public SignedOutException() {
    }

    /**
     * Constructs an instance of <code>SignedOutException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SignedOutException(String msg) {
        super(msg);
    }
}
