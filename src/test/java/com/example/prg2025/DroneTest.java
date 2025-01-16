package com.example.prg2025;


import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Status;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.models.Station;
import com.example.prg2025.services.DeliveryService;
import com.example.prg2025.services.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DroneTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService;

    private Drone drone;
    private Station station;

    @BeforeEach
    public void setUp() {
        station = new Station();
        station.setId(1L);
        drone = new Drone(station);
        drone.setId(1L);
        drone.setStatus(Status.WORKING);
    }

    @Test
    public void testChangeDroneStatus_Success() {

        when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));

        HttpStatus status = droneService.changeDroneStatus(1L, Status.PHASED_OUT);
        assertEquals(HttpStatus.OK, status);
        assertEquals(Status.PHASED_OUT, drone.getStatus());

        verify(droneRepository, times(1)).save(drone);
    }


}