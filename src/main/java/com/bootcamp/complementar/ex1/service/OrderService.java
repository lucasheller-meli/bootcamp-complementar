package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.dto.OrderRequest;
import com.bootcamp.complementar.ex1.dto.OrderResponse;
import com.bootcamp.complementar.ex1.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishService dishService;

    public OrderService(OrderRepository orderRepository, DishService dishService) {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
    }

    public Map<Long, OrderResponse> all() {
        return orderRepository.all()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> OrderResponse.from(entry.getValue(), dishService)));
    }

    public OrderResponse get(Long id) {
        return OrderResponse.from(orderRepository.get(id), dishService);
    }

    public OrderResponse create(OrderRequest order) throws IOException {
        return OrderResponse.from(orderRepository.create(order), dishService);
    }

    public OrderResponse update(Long id, OrderRequest order) throws IOException {
        return OrderResponse.from(orderRepository.update(id, order), dishService);
    }

    public OrderResponse delete(Long id) throws IOException {
        return OrderResponse.from(orderRepository.delete(id), dishService);
    }
}
