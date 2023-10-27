package org.accio.Library.Management.System.Exceptions;

public class AuthorListEmptyException extends Exception {
    public AuthorListEmptyException() {
        super("The author list is empty");
    }

    public AuthorListEmptyException(String message) {
        super(message);
    }
}