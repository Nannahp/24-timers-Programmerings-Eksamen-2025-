package com.example.prg2025.services;

import com.example.prg2025.exceptions.*;
import com.example.prg2025.models.*;
import com.example.prg2025.repository.DeliveryRepository;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.repository.PizzaRepository;
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

    public List<Delivery> findAllNonDelivered() {
        return deliveryRepository.findAllNonDelivered();
    }

    public Delivery addDelivery(Long pizzaId, String address) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty()) {
            throw new PizzaNotFoundException("Pizza with ID " + pizzaId + " not found.");
        }
        Pizza chosenPizza = pizza.get();
        Delivery newDelivery = new Delivery(chosenPizza, address);
        newDelivery.setExpectedArrival();
        deliveryRepository.save(newDelivery);
        return newDelivery;
    }

    public List<Delivery> findAllDeliveriesWithoutDrones() {
        return deliveryRepository.findAllDeliveriesWithoutDrones();
    }

    public Delivery assignDrone(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        if (delivery.isEmpty()) {
            throw new DeliveryNotFoundException("Delivery with ID " + delivery + " not found.");
        }
        Delivery updatedDelivery = delivery.get();
        if (updatedDelivery.getDrone() != null ){
            throw new DeliveryAlreadyHasDroneExceptions("Delivery with ID " + updatedDelivery + " already has drone with Id: " + updatedDelivery.getDrone().getId());
        }
        List<Drone> workingDrones = droneRepository.findDronesWithStatus(Status.WORKING);
        if (workingDrones.isEmpty()) {
            throw new NoWorkingDronesException("No Working drones in database");
        }

        Drone randomDrone = workingDrones.get(random.nextInt(workingDrones.size()));
        updatedDelivery.setDrone(randomDrone);
        deliveryRepository.save(updatedDelivery);

        return updatedDelivery;
    }

    public Delivery finishDelivery(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        if (delivery.isEmpty()) {
            throw new DeliveryNotFoundException("Delivery with ID " + delivery + " not found.");
        }
        Delivery finishedDelivery = delivery.get();
        if  (finishedDelivery.getDrone() == null) {
            throw new FinishedDeliveryaHasNoDroneException("Delivery with ID " + delivery + " has dno  drone.");
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
