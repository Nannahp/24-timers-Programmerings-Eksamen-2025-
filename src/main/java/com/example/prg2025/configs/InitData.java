package com.example.prg2025.configs;


import com.example.prg2025.models.*;
import com.example.prg2025.repository.DeliveryRepository;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.repository.PizzaRepository;
import com.example.prg2025.repository.StationRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

    @Component
    public class InitData implements CommandLineRunner {

        private DroneRepository droneRepository;
        private StationRepository stationRepository;
        private PizzaRepository pizzaRepository;
        private DeliveryRepository deliveryRepository;

        public InitData(DroneRepository droneRepository,
                        StationRepository stationRepository,
                        PizzaRepository pizzaRepository,
                        DeliveryRepository deliveryRepository) {
            this.deliveryRepository = deliveryRepository;
            this.droneRepository = droneRepository;
            this.pizzaRepository = pizzaRepository;
            this.stationRepository = stationRepository;
        }

        @Override
        @Transactional
        public void run(String... args) throws Exception {


            Pizza pepperoni = new Pizza("Pepperoni", 70);
            Pizza vesuvius = new Pizza("Vesuvius", 70);
            Pizza amager = new Pizza("Amager", 100);
            Pizza magarita = new Pizza("Magarita", 60);
            Pizza mexican = new Pizza("Mexican", 80);

            pizzaRepository.saveAll(Arrays.asList(pepperoni, vesuvius, amager, magarita, mexican));


            Station stationA = new Station(55.67, 12.58);
            Station stationB = new Station(55.69, 12.55);
            Station stationC = new Station(55.66, 12.60);

            stationRepository.saveAll(Arrays.asList(stationA, stationB, stationC));


            Delivery delivery1 = new Delivery(pepperoni, "Jagtvej 51");
            Delivery delivery2 = new Delivery(pepperoni, "Bredgade 11");
            delivery2.setActualArrival(LocalDateTime.now().plusHours(1));
            Delivery delivery3 = new Delivery(amager, "HC Andersens Bls 1");


            deliveryRepository.save(delivery1);
            deliveryRepository.save(delivery2);

            Drone drone1 = new Drone(stationA);

            droneRepository.save(drone1);
            drone1.addDelivery(delivery3);
            delivery3.setDrone(drone1);

            stationA.addDrone(drone1);
            stationRepository.save(stationA);

           deliveryRepository.save(delivery3);
        }
    }

