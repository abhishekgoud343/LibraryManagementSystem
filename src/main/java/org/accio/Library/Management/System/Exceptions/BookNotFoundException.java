package org.accio.Library.Management.System.Exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("The book Id is invalid");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}