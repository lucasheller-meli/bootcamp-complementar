package com.bootcamp.complementar.ex1.controller;

import com.bootcamp.complementar.ex1.dto.OrderRequest;
import com.bootcamp.complementar.ex1.dto.OrderResponse;
import com.bootcamp.complementar.ex1.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Map<Long, OrderResponse>> all() {
        return ResponseEntity.ok(orderService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest order) throws IOException {
        OrderResponse savedOrder = orderService.create(order);

        return ResponseEntity.created(URI.create(String.format("/orders/%s", savedOrder.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest order) throws IOException {
        return ResponseEntity.ok(orderService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> delete(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(orderService.delete(id));
    }
}
