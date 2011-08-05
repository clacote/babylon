package org.plug.babylon.service;

/**
 * Exception of import process
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class ImportException extends Exception {

    /**
     * Creates a new instance of <code>ImportException</code> without detail message.
     */
    public ImportException() {
    }

    /**
     * Constructs an instance of <code>ImportException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ImportException(String msg) {
        super(msg);
    }
}
