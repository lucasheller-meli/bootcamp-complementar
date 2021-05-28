package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.entity.Dish;
import com.bootcamp.complementar.ex1.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Map<Long, Dish> all() {
        return dishRepository.all();
    }

    public Dish create(Dish dish) throws IOException {
        return dishRepository.create(dish);
    }
}
