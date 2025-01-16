package com.example.prg2025.services;

import com.example.prg2025.models.*;
import com.example.prg2025.repository.DeliveryRepository;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    DroneRepository droneRepository;
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

    public HttpStatus addDelivery(Long pizzaId, String address) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if(pizza.isEmpty()) {
            return HttpStatus.NO_CONTENT;
        }
        Pizza choosenPizza  = pizza.get();
        Delivery newDelivery = new Delivery(choosenPizza,address);
        newDelivery.setExpectedArrival();
        deliveryRepository.save(newDelivery);
        return HttpStatus.CREATED;
    }

    public List<Delivery> findAllDeliveriesWithoutDrones() {
        return deliveryRepository.findAllDeliveriesWithoutDrones();
    }

    public HttpStatus assignDrone(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        Delivery updatedDelivery = delivery.get();
        if (delivery.isEmpty() || updatedDelivery.getDrone()!= null) {
             return HttpStatus.BAD_REQUEST;
        }

        List<Drone> workingDrones = droneRepository.findDronesWithStatus(Status.WORKING);
        if (workingDrones.isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        Drone randomDrone = workingDrones.get(random.nextInt(workingDrones.size()));
        updatedDelivery.setDrone(randomDrone);
        deliveryRepository.save(updatedDelivery);
        return HttpStatus.OK;
    }

    public HttpStatus finishDelivery(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        if (delivery.isEmpty())
        {
            return HttpStatus.NOT_FOUND;
        }
        Delivery finishedDelivery = delivery.get();
        if  (finishedDelivery.getDrone() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        LocalDateTime arrivalTime = LocalDateTime.now();
        finishedDelivery.setActualArrival(arrivalTime);
        deliveryRepository.save(finishedDelivery);
        return HttpStatus.OK;
    }
}
