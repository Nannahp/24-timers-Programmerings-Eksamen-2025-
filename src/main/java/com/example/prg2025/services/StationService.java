package com.example.prg2025.services;

import com.example.prg2025.models.Station;
import com.example.prg2025.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {


    @Autowired
    private StationRepository stationRepository;

    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    public List<Station> stationsSortedByLeastNumberOfDrones() {
        return  stationRepository.findStationWithLeastDrones();
    }

    public void saveStation(Station station) {
        stationRepository.save(station);
    }

}
