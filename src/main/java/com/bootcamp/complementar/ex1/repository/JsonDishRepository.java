package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.DishDTO;
import com.bootcamp.complementar.ex1.entity.Dish;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

@Repository
public class JsonDishRepository implements DishRepository {
    private Map<Long, Dish> dishes;
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<Map<Long, Dish>> type = new TypeReference<>() {};
    private File file;
    private Long nextId;

    @PostConstruct
    private void loadDatabase() throws IOException {
        this.file = ResourceUtils.getFile("classpath:dish.json");

        this.dishes = mapper.readValue(file, type);

        this.nextId = dishes.keySet().stream().max(Comparator.naturalOrder()).orElse(0L);
    }

    public Map<Long, Dish> all() {
        return dishes;
    }

    public Dish get(Long id) {
        return dishes.get(id);
    }

    public Dish create(DishDTO dishDTO) throws IOException {
        return save(nextId, dishDTO);
    }

    public Dish update(Long id, DishDTO dishDTO) throws IOException {
        return save(id, dishDTO);
    }

    public Dish delete(Long id) throws IOException {
        Dish dish = dishes.remove(id);

        mapper.writeValue(file, dishes);

        return dish;
    }

    private Dish save(Long id, DishDTO dishDTO) throws IOException {
        Dish dish = Dish.builder()
                .id(id)
                .description(dishDTO.getDescription())
                .price(dishDTO.getPrice())
                .build();

        dishes.put(id, dish);

        mapper.writeValue(file, dishes);

        return dish;
    }
}
