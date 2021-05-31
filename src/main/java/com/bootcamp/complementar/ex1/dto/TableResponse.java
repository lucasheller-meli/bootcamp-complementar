package com.bootcamp.complementar.ex1.dto;

import com.bootcamp.complementar.ex1.entity.Order;
import com.bootcamp.complementar.ex1.entity.Table;
import com.bootcamp.complementar.ex1.service.DishService;
import com.bootcamp.complementar.ex1.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableResponse {
    private Long id;
    private List<OrderResponse> orders;
    private Double totalPrice;
    private Boolean closed;

    public static TableResponse from(Table table, OrderService orderService) {
        return TableResponse.builder()
                .id(table.getId())
                .orders(table.orders(orderService))
                .totalPrice(table.totalPrice(orderService))
                .closed(table.getClosed())
                .build();
    }
}
