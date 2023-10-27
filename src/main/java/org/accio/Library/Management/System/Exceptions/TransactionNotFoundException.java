package org.accio.Library.Management.System.Exceptions;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException() {
        super("The transaction Id is invalid");
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}