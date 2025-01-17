package com.example.prg2025;

import com.example.prg2025.exceptions.NoStationsFoundException;
import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Status;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.models.Station;
import com.example.prg2025.services.DroneService;
import com.example.prg2025.services.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DroneTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private StationService stationService;
    @InjectMocks
    private DroneService droneService;

    private Drone mockDrone;
    private Station mockStation;

    @BeforeEach
    public void setUp() {
        mockStation = new Station();
        mockDrone = new Drone(mockStation);
        mockDrone.setId(1L);
    }

    @Test
    public void testAddDrone_Success() {
        List<Station> stations = new ArrayList<>();
        stations.add(mockStation);
        when(stationService.stationsSortedByLeastNumberOfDrones()).thenReturn(stations);

        when(droneRepository.save(any(Drone.class))).thenReturn(mockDrone);

        Drone newDrone = droneService.addDrone();

        assertEquals(mockStation, newDrone.getStation());
        verify(droneRepository, times(1)).save(newDrone);
        verify(stationService, times(1)).saveStation(mockStation);
    }
    @Test
    public void testAddDrone_Failure_NoStations() {
        when(stationService.stationsSortedByLeastNumberOfDrones()).thenReturn(null);

        try {
            droneService.addDrone();
        } catch (NoStationsFoundException e) {
            assertEquals("No stations in database", e.getMessage());
        }

        verify(droneRepository, times(0)).save(any(Drone.class));
        verify(stationService, times(0)).saveStation(any(Station.class));
    }
    @Test
    public void testChangeDroneStatus_Success() {
        when(droneRepository.findById(1L)).thenReturn(Optional.of(mockDrone));
        Drone updatedDrone = droneService.changeDroneStatus(1L, Status.PHASED_OUT);
        assertEquals(Status.PHASED_OUT, updatedDrone.getStatus());

        verify(droneRepository, times(1)).save(mockDrone);
    }


}