package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.entity.Restaurant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Repository
public class JsonRestaurantRepository implements RestaurantRepository {
    private Restaurant restaurant;
    private final ObjectMapper mapper = new ObjectMapper();
    private File file;

    @PostConstruct
    private void loadDatabase() throws IOException {
        this.file = ResourceUtils.getFile("classpath:restaurant.json");

        TypeReference<Restaurant> type = new TypeReference<>() {};
        this.restaurant = mapper.readValue(file, type);

        if (restaurant.getCash() == null) restaurant.setCash(0.0);
    }

    public Restaurant get() {
        return restaurant;
    }

    public void addCash(Double cash) throws IOException {
        restaurant.setCash(restaurant.getCash() + cash);

        mapper.writeValue(file, restaurant);

    }
}
