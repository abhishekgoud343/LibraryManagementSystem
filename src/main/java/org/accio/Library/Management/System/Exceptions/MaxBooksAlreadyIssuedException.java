package org.accio.Library.Management.System.Exceptions;

public class MaxBooksAlreadyIssuedException extends Exception {
    public MaxBooksAlreadyIssuedException() {
        super("The maximum book issue limit has been reached for this student");
    }

    public MaxBooksAlreadyIssuedException(String message) {
        super(message);
    }
}