package com.example.prg2025.controllers;

import com.example.prg2025.models.Delivery;
import com.example.prg2025.models.DeliveryRequest;
import com.example.prg2025.services.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Delivery> addDelivery(@RequestBody DeliveryRequest request) {
        System.out.println("Received pizzaId: " + request.pizzaId());
        System.out.println("Received address: " + request.address());
        Optional<Delivery> newDelivery = deliveryService.addDelivery(request.pizzaId(), request.address());
        if (newDelivery.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println("Created delivery: " + newDelivery.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(newDelivery.get());
    }

    @GetMapping("/queue")
    public List<Delivery> getDeliveriesWithoutDrones(){
        return deliveryService.findAllDeliveriesWithoutDrones();
    }

    @PostMapping("schedule")
    public ResponseEntity<Delivery> assignDroneToDelivery(@RequestParam Long deliveryId) {

        ResponseEntity<Delivery> response = deliveryService.assignDrone(deliveryId);

        return response;
    }
    @PostMapping("finish")
    public ResponseEntity<Delivery> finishDelivery(@RequestParam Long deliveryId) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(deliveryId);

        if (delivery.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Delivery updatedDelivery = deliveryService.finishDelivery(deliveryId);
        if (updatedDelivery == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(updatedDelivery);
    }

}
