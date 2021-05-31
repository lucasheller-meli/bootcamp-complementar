package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.dto.OrderRequest;
import com.bootcamp.complementar.ex1.dto.OrderResponse;
import com.bootcamp.complementar.ex1.entity.Order;
import com.bootcamp.complementar.ex1.repository.OrderRepository;
import com.bootcamp.complementar.ex1.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final DishService dishService;
    private final RestaurantService restaurantService;

    public OrderService(OrderRepository orderRepository, TableRepository tableRepository, DishService dishService, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.dishService = dishService;
        this.restaurantService = restaurantService;
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
        Order savedOrder = orderRepository.create(order);

        tableRepository.addOrder(savedOrder);

        return OrderResponse.from(savedOrder, dishService);
    }

    public OrderResponse update(Long id, OrderRequest order) throws IOException {
        return OrderResponse.from(orderRepository.update(id, order), dishService);
    }

    public OrderResponse delete(Long id) throws IOException {
        return OrderResponse.from(orderRepository.delete(id), dishService);
    }

    public void close(OrderResponse order) throws IOException {
        restaurantService.addCash(order.getTotalPrice());
        orderRepository.close(order.getId());
    }
}
