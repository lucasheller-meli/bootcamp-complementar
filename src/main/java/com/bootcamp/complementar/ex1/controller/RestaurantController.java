package com.bootcamp.complementar.ex1.controller;

import com.bootcamp.complementar.ex1.entity.Restaurant;
import com.bootcamp.complementar.ex1.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<Restaurant> get() {
        return ResponseEntity.ok(restaurantService.get());
    }
}
