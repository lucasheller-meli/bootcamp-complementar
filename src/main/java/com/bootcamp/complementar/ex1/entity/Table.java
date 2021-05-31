package com.bootcamp.complementar.ex1.entity;

import com.bootcamp.complementar.ex1.dto.OrderResponse;
import com.bootcamp.complementar.ex1.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    private Long id;
    private List<Long> orderIds;

    public List<OrderResponse> orders(OrderService orderService) {
        return orderIds.stream().map(orderService::get).collect(Collectors.toList());
    }

    public Double totalPrice(OrderService orderService) {
        return orders(orderService).stream().mapToDouble(OrderResponse::getTotalPrice).sum();
    }
}
