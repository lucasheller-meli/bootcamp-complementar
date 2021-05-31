package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.entity.Restaurant;
import com.bootcamp.complementar.ex1.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant get() {
        return restaurantRepository.get();
    }

    public void addCash(Double cash) throws IOException {
        restaurantRepository.addCash(cash);
    }
}
