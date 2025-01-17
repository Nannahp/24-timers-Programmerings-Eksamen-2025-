package com.example.prg2025.services;

import com.example.prg2025.exceptions.DroneNotFoundException;
import com.example.prg2025.exceptions.NoStationsFoundException;
import com.example.prg2025.exceptions.NoWorkingDronesException;
import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Station;
import com.example.prg2025.models.Status;
import com.example.prg2025.repository.DroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DroneService {


    private DroneRepository droneRepository;
    private StationService stationService;

public DroneService(DroneRepository droneRepository, StationService stationService) {
    this.droneRepository=droneRepository;
    this.stationService = stationService;
}

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    public Drone  addDrone() {
        List<Station> stations = stationService.stationsSortedByLeastNumberOfDrones();
        if(stations == null) {
            throw new NoStationsFoundException();
        }
        Station smallestStation  =stations.getFirst();
        Drone newDrone = new Drone(smallestStation);
        smallestStation.addDrone(newDrone);
        droneRepository.save(newDrone);
        stationService.saveStation(smallestStation);
        return newDrone;
    }

    public Drone changeDroneStatus(Long droneId, Status status) {
        Optional<Drone> drone = droneRepository.findById(droneId);
        if (drone.isEmpty()) {
            throw new DroneNotFoundException("Drone with id: " + droneId+ "not found");
        }
        Drone existingDrone = drone.get();
        existingDrone.setStatus(status);
        droneRepository.save(existingDrone);
        return existingDrone;

    }
}
