package com.example.prg2025.exceptions;

public class NoStationsFoundException extends RuntimeException {
    public NoStationsFoundException() {
        super("No stations in database");
    }
}
