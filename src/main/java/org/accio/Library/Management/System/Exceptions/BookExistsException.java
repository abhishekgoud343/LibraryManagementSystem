package org.accio.Library.Management.System.Exceptions;

public class BookExistsException extends Exception {
    public BookExistsException() {
        super("The book already exists in DB");
    }

    public BookExistsException(String message) {
        super(message);
    }
}