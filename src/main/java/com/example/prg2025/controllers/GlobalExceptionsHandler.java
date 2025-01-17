package com.example.prg2025.controllers;

import com.example.prg2025.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {


    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<String> handlePizzaNotFound(PizzaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<String> handleDeliveryNotFound(DeliveryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DroneNotFoundException.class)
    public ResponseEntity<String> handleDroneNotFound(DroneNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoStationsFoundException.class)
    public ResponseEntity<String> handleNoStationsFound(NoStationsFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoWorkingDronesException.class)
    public ResponseEntity<String> handleNoWorkingDronesFound(NoWorkingDronesException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
    }

    @ExceptionHandler(DeliveryAlreadyHasDroneExceptions.class)
    public ResponseEntity<String> handleDeliveryAlreadyHasDrone(DeliveryAlreadyHasDroneExceptions ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FinishedDeliveryaHasNoDroneException.class)
    public ResponseEntity<String> handleFinishedDeliveryHasNoDrone(FinishedDeliveryaHasNoDroneException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}

