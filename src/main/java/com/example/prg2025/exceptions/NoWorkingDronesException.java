package com.example.prg2025.exceptions;

public class NoWorkingDronesException extends RuntimeException {
    public NoWorkingDronesException(String message) {
        super(message);
    }
}
