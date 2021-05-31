package com.bootcamp.complementar.ex1.dto;

import com.bootcamp.complementar.ex1.entity.Dish;
import com.bootcamp.complementar.ex1.entity.Order;
import com.bootcamp.complementar.ex1.service.DishService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long tableId;
    private Map<Dish, Integer> dishQuantities;
    private Double totalPrice;
    private Boolean payed;

    public static OrderResponse from(Order order, DishService dishService) {
        return OrderResponse.builder()
                .id(order.getId())
                .tableId(order.getTableId())
                .dishQuantities(order.instantiatedDishQuantities(dishService))
                .totalPrice(order.totalPrice(dishService))
                .payed(order.getPayed())
                .build();
    }
}
