package com.example.prg2025.services;

import com.example.prg2025.exceptions.*;
import com.example.prg2025.models.*;
import com.example.prg2025.repository.DeliveryRepository;
import com.example.prg2025.repository.DroneRepository;
import com.example.prg2025.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    private Pizza pizza;
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        pizza = new Pizza("amager", 10);
        pizza.setId(1L);
        delivery = new Delivery(pizza, "Test Address");
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testAddDelivery_ValidPizza() {
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
        when(deliveryRepository.save(Mockito.any(Delivery.class))).thenReturn(delivery);

        Delivery newDelivery = deliveryService.addDelivery(1L, "Test Address");
        assertNotNull(newDelivery);
        assertEquals("Test Address", newDelivery.getAddress());
        assertEquals(pizza, newDelivery.getPizza());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testAddDelivery_PizzaNotFound() {
        when(pizzaRepository.findById(1L)).thenReturn(Optional.empty());
        PizzaNotFoundException exception = assertThrows(PizzaNotFoundException.class, () -> {
            deliveryService.addDelivery(1L, "Test Address");
        });
        assertEquals("Pizza with ID 1 not found.", exception.getMessage());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testAssignDrone_Valid() {
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));
        Drone workingDrone = new Drone();
        workingDrone.setStatus(Status.WORKING);
        when(droneRepository.findDronesWithStatus(Status.WORKING)).thenReturn(List.of(workingDrone));
        when(deliveryRepository.save(Mockito.any(Delivery.class))).thenReturn(delivery);

        Delivery updatedDelivery = deliveryService.assignDrone(1L);

        assertNotNull(updatedDelivery.getDrone());
        assertEquals(workingDrone, updatedDelivery.getDrone());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testAssignDrone_NoWorkingDrones() {
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));
        when(droneRepository.findDronesWithStatus(Status.WORKING)).thenReturn(List.of());
        NoWorkingDronesException exception = assertThrows(NoWorkingDronesException.class, () -> {
            deliveryService.assignDrone(1L);
        });
        assertEquals("No Working drones in database", exception.getMessage());
    }
}
