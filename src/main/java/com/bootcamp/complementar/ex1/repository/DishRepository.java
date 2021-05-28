package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.entity.Dish;

import java.io.IOException;
import java.util.Map;

public interface DishRepository {
    Map<Long, Dish> all();
    Dish create(Dish dish) throws IOException;
}
