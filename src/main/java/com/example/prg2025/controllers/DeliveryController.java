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
        Delivery newDelivery = deliveryService.addDelivery(request.pizzaId(), request.address());
        return ResponseEntity.status(HttpStatus.CREATED).body(newDelivery);
    }

    @GetMapping("/queue")
    public List<Delivery> getDeliveriesWithoutDrones(){
        return deliveryService.findAllDeliveriesWithoutDrones();
    }

    @PostMapping("schedule")
    public ResponseEntity<Delivery> assignDroneToDelivery(@RequestParam Long deliveryId) {
        Delivery updatedDelivery= deliveryService.assignDrone(deliveryId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDelivery);
    }

    @PostMapping("finish")
    public ResponseEntity<Delivery> finishDelivery(@RequestParam Long deliveryId) {
        Delivery finishedDelivery = deliveryService.finishDelivery(deliveryId);
        System.out.println(finishedDelivery.toString());
        return ResponseEntity.status(HttpStatus.OK).body(finishedDelivery);
    }

}
