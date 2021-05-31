package com.bootcamp.complementar.ex1.entity;

import com.bootcamp.complementar.ex1.service.DishService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long tableId;
    private Map<Long, Integer> dishQuantities;

    public Map<Dish, Integer> instantiatedDishQuantities(DishService dishService) {
        return dishQuantities.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> dishService.get(entry.getKey()), Map.Entry::getValue));
    }

    public Double totalPrice(DishService dishService) {
        return instantiatedDishQuantities(dishService).entrySet()
                .stream()
                .mapToDouble((entry) -> entry.getKey().getPrice() * entry.getValue()).sum();
    }
}
