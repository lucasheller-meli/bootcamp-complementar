package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.entity.Restaurant;

import java.io.IOException;

public interface RestaurantRepository {
    Restaurant get();
    void addCash(Double cash) throws IOException;
}
