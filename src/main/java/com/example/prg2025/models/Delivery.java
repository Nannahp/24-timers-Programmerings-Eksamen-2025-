package com.example.prg2025.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime expectedArrival;
    private LocalDateTime actualArrival;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "drone_id", nullable = true)
    private Drone drone;


    private String address;

     public Delivery(Pizza pizza, String address) {
        this.pizza = pizza;
        this.address = address;
    }



    public Delivery() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setExpectedArrival() {
        this.expectedArrival = LocalDateTime.now().plusMinutes(30);
    }

    public LocalDateTime getActualArrival() {
        return actualArrival;
    }

    public LocalDateTime getExpectedArrival() {
         return this.expectedArrival;
    }
    public void setActualArrival(LocalDateTime actualArrival) {
        this.actualArrival = actualArrival;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
