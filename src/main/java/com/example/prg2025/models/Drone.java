package com.example.prg2025.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID serialUuid;

    @OneToMany(mappedBy = "drone")
    @JsonBackReference
    private List<Delivery> deliveries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    @JsonManagedReference
    private Station station;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Drone(Station station){
        this();
        this.station = station;
        station.addDrone(this);
    }

    public Drone() {
        this.serialUuid = UUID.randomUUID();
        this.status = Status.WORKING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSerialUuid() {
        return serialUuid;
    }

    public void setSerialUuid(UUID serialUuid) {
        this.serialUuid = serialUuid;
    }

    public List<Delivery> getDeliveries() {
        return new ArrayList<>(deliveries);
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = new ArrayList<>(deliveries);
    }

    public void addDelivery(Delivery delivery) {
        if (delivery != null) {
            this.deliveries.add(delivery);
        }
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}