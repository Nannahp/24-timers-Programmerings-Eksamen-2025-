package com.example.prg2025.controllers;

import com.example.prg2025.models.Drone;
import com.example.prg2025.models.Pizza;
import com.example.prg2025.services.DroneService;
import com.example.prg2025.services.PizzaService;
import com.example.prg2025.services.StationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"https://127.0.0.1:5173", "https://localhost:5173"})
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private PizzaService pizzaService;


    public PizzaController( PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @GetMapping("")
    public List<Pizza> getPizzas(){
        return pizzaService.findAll();
    }

}
