package org.plug.babylon.service;

/**
 * Unmanaged file type for impot
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class UnmanagedFileTypeException extends ImportException {

    /** Name of file tried to be imported */
    private String fileName;

    /**
     * Creates a new instance of <code>UnknownFileTypeException</code> without detail message.
     */
    public UnmanagedFileTypeException(final String fileName) {
        super("Unmanaged file type for " + fileName + ": Unable to import");
        this.fileName = fileName;
    }
}
