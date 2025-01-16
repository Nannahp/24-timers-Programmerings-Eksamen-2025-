package com.example.prg2025.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double longitude;
    private double latitude;

    @OneToMany(mappedBy = "station")
    @JsonBackReference
    private List<Drone> drones = new ArrayList<>();

    public Station(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Station() {}

    public void addDrone(Drone drone) {
        this.drones.add(drone);
        drone.setStation(this);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }
}
