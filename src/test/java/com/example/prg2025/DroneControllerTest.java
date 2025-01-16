package com.example.prg2025;

import com.example.prg2025.controllers.DroneController;
import com.example.prg2025.services.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
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


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddDrone_Success() throws Exception {

        when(droneService.addDrone()).thenReturn(HttpStatus.CREATED);

        mockMvc.perform(post("/drones/add"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Drone created successfully."))
                .andReturn();

        verify(droneService, times(1)).addDrone();
    }

    @Test
    public void testAddDrone_Failure() throws Exception {
        when(droneService.addDrone()).thenReturn(HttpStatus.NOT_FOUND);

        mockMvc.perform(post("/drones/add"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No stations."))
                .andReturn();

        verify(droneService, times(1)).addDrone();
    }
}