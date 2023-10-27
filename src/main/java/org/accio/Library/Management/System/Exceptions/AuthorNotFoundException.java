package org.accio.Library.Management.System.Exceptions;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException() {
        super("The author Id is invalid");
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}