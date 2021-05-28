package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.DishDTO;
import com.bootcamp.complementar.ex1.entity.Dish;

import java.io.IOException;
import java.util.Map;

public interface DishRepository {
    Map<Long, Dish> all();
    Dish get(Long id);
    Dish create(DishDTO dishDTO) throws IOException;
    Dish update(Long id, DishDTO dishDTO) throws IOException;
    Dish delete(Long id) throws IOException;
}
