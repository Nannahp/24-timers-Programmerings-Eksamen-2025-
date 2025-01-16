package com.example.prg2025.controllers;

import com.example.prg2025.models.Delivery;
import com.example.prg2025.services.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://127.0.0.1:5173", "https://localhost:5173"})
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService ){
        this.deliveryService = deliveryService;
    }

    @GetMapping("")
    public List<Delivery> getDeliveries() {
        return deliveryService.findAllNonDelivered();
    }
    @PostMapping("/add")
    public ResponseEntity<String> addDelivery(@RequestParam Long pizzaId, @RequestParam String address){
        HttpStatus status = deliveryService.addDelivery(pizzaId, address);
        if (status == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pizza doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Delivery created");
    }

    @GetMapping("/queue")
    public List<Delivery> getDeliveriesWithoutDrones(){
        return deliveryService.findAllDeliveriesWithoutDrones();
    }

    @PostMapping("schedule")
    public ResponseEntity<String> assignDroneToDelivery(@RequestParam Long deliveryId) {
        HttpStatus status = deliveryService.assignDrone(deliveryId);
        if (status == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Delivery has a drone.");
        }
        if (status == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No drone avaliable");
        }
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Drone assigned to delivery");
        }

        @PostMapping("/finish")
    public ResponseEntity<String> finishDelivery(@RequestParam Long deliveryId) {
        HttpStatus status = deliveryService.finishDelivery(deliveryId);
            if (status == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Delivery doesn't exist.");
            }
            if (status == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No drone assigned to delivery");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Delivery finished");

        }

}
