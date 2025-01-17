package com.example.prg2025;

import com.example.prg2025.controllers.DroneController;
import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Station;
import com.example.prg2025.models.Status;
import com.example.prg2025.services.DroneService;
import com.example.prg2025.services.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DroneController.class)
public class DroneControllerTest {

    @MockitoBean
    private DroneService droneService;
    @MockitoBean
    private StationService stationService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddDrone_Success() throws Exception {

        Station mockStation = new Station();
        Drone mockDrone = new Drone(mockStation);
        mockDrone.setId(1L);
        when(droneService.addDrone()).thenReturn(mockDrone);


        mockMvc.perform(post("/drones/add")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockDrone.getId()))
                .andExpect(jsonPath("$.status").value(mockDrone.getStatus().toString()))
                .andReturn();

        verify(droneService, times(1)).addDrone();
    }

    @Test
    public void testAddDrone_Failure() throws Exception {
//returns 201 not 404
        when(stationService.stationsSortedByLeastNumberOfDrones()).thenReturn(null);

        mockMvc.perform(post("/drones/add")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No stations in database"))
                .andReturn();

        verify(stationService, times(1)).stationsSortedByLeastNumberOfDrones();
    }
}