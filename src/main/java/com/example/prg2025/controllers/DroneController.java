package com.example.prg2025.controllers;

import com.example.prg2025.models.Drone;

import com.example.prg2025.models.Station;
import com.example.prg2025.models.Status;
import com.example.prg2025.services.DroneService;

import com.example.prg2025.services.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://127.0.0.1:5173", "https://localhost:5173"})
@RestController
@RequestMapping("/drones")
public class DroneController {


    private DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;

    }


    @GetMapping("")
    public List<Drone> getDrones(){
        return droneService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<String> addDrone() {
        HttpStatus status = droneService.addDrone();

        if (status == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No stations.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Drone created successfully.");
    }


    @PostMapping("/enable")
    public ResponseEntity<String> enabledDrone(@RequestParam Long droneId) {
        HttpStatus status = droneService.changeDroneStatus(droneId, Status.WORKING);
        return returndDroneStatusStatement(status);
    }

    @PostMapping("/disable")
    public ResponseEntity<String> disabledDrone(@RequestParam Long droneId) {
        HttpStatus status = droneService.changeDroneStatus(droneId, Status.OUT_OF_SERVICE);
        return returndDroneStatusStatement(status);
    }
    @PostMapping("/retire")
    public ResponseEntity<String> retireDrone(@RequestParam Long droneId) {
        HttpStatus status = droneService.changeDroneStatus(droneId, Status.PHASED_OUT);
        return returndDroneStatusStatement(status);
    }

    public ResponseEntity<String> returndDroneStatusStatement(HttpStatus status) {
        if (status == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Drone doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Drone status changed.");
    }
}

