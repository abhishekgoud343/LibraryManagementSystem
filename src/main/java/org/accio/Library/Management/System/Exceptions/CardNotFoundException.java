package org.accio.Library.Management.System.Exceptions;

public class CardNotFoundException extends Exception {
    public CardNotFoundException() {
        super("The card Id is invalid");
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}