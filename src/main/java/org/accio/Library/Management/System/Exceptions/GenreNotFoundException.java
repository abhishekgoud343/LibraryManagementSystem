package org.accio.Library.Management.System.Exceptions;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException() {
        super("The genre is null or invalid");
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
