package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.OrderRequest;
import com.bootcamp.complementar.ex1.entity.Order;

import java.io.IOException;
import java.util.Map;

public interface OrderRepository {
    Map<Long, Order> all();
    Order get(Long id);
    Order create(OrderRequest orderRequest) throws IOException;
    Order update(Long id, OrderRequest orderRequest) throws IOException;
    Order delete(Long id) throws IOException;
    void close(Long id) throws IOException;
}
