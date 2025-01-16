package com.example.prg2025.services;

import com.example.prg2025.models.*;
import com.example.prg2025.repository.DeliveryRepository;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class DeliveryService {
    private final DroneRepository droneRepository;
    private final PizzaRepository pizzaRepository;
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DroneRepository droneRepository, PizzaRepository pizzaRepository, DeliveryRepository deliveryRepository) {
        this.droneRepository = droneRepository;
        this.pizzaRepository = pizzaRepository;
        this.deliveryRepository = deliveryRepository;
    }
    Random random = new Random();

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Transactional
    public void createDelivery(Delivery delivery) {
            deliveryRepository.save(delivery);
        }

    public List<Delivery> findAllNonDelivered() {
        return deliveryRepository.findAllNonDelivered();
    }

    public Optional<Delivery> addDelivery(Long pizzaId, String address) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty()) {
            return Optional.empty();
        }
        Pizza chosenPizza = pizza.get();
        Delivery newDelivery = new Delivery(chosenPizza, address);
        newDelivery.setExpectedArrival();
        deliveryRepository.save(newDelivery);
        return Optional.of(newDelivery);
    }

    public List<Delivery> findAllDeliveriesWithoutDrones() {
        return deliveryRepository.findAllDeliveriesWithoutDrones();
    }

    public ResponseEntity<Delivery> assignDrone(Long deliveryId) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryId);
        if (deliveryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Delivery updatedDelivery = deliveryOptional.get();

        if (updatedDelivery.getDrone() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<Drone> workingDrones = droneRepository.findDronesWithStatus(Status.WORKING);
        if (workingDrones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


        Drone randomDrone = workingDrones.get(random.nextInt(workingDrones.size()));
        updatedDelivery.setDrone(randomDrone);
        deliveryRepository.save(updatedDelivery);


        return ResponseEntity.status(HttpStatus.OK).body(updatedDelivery);
    }

    public Delivery finishDelivery(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        if (delivery.isEmpty())
        {
            return null;
        }
        Delivery finishedDelivery = delivery.get();
        if  (finishedDelivery.getDrone() == null) {
            return null;
        }
        LocalDateTime arrivalTime = LocalDateTime.now();
        finishedDelivery.setActualArrival(arrivalTime);
        deliveryRepository.save(finishedDelivery);
        return  finishedDelivery;
    }

    public Optional<Delivery> getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId);
    }
}
