package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.dto.DishDTO;
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

    public Dish get(Long id) {
        return dishRepository.get(id);
    }

    public Dish create(DishDTO dish) throws IOException {
        return dishRepository.create(dish);
    }

    public Dish update(Long id, DishDTO dishDTO) throws IOException {
        return dishRepository.update(id, dishDTO);
    }

    public Dish delete(Long id) throws IOException {
        return dishRepository.delete(id);
    }
}
