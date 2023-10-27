package org.accio.Library.Management.System.Exceptions;

public class InvalidCardStatusException extends Exception {
    public InvalidCardStatusException() {
        super("The status of this card is invalid for the transaction");
    }

    public InvalidCardStatusException(String message) {
        super(message);
    }
}