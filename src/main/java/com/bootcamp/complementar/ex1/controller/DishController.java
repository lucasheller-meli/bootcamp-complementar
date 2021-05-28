package com.bootcamp.complementar.ex1.controller;

import com.bootcamp.complementar.ex1.entity.Dish;
import com.bootcamp.complementar.ex1.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/dishes")
public class DishController {
    public final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<Map<Long, Dish>> all() {
        return ResponseEntity.ok(dishService.all());
    }

    @PostMapping
    public ResponseEntity<Dish> create(@RequestBody Dish dish) throws IOException {
        Dish savedDish = dishService.create(dish);

        return ResponseEntity.created(URI.create(String.format("/dishes/%s", savedDish.getId()))).build();
    }
}
