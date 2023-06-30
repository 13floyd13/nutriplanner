package com.forrest.nutriplanner.exceptions;

public class InvalidBarcodeException extends Exception {
    public InvalidBarcodeException (String message, Throwable cause) {
        super(message, cause);
    }
}
