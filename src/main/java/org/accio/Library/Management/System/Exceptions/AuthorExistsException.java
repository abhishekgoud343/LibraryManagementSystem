package org.accio.Library.Management.System.Exceptions;

public class AuthorExistsException extends Exception {
    public AuthorExistsException() {
        super("The author already exists in DB");
    }

    public AuthorExistsException(String message) {
        super(message);
    }
}