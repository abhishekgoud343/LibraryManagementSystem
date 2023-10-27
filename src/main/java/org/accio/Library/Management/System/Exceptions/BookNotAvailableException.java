package org.accio.Library.Management.System.Exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException() {
        super("The book is not available");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}