package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.OrderRequest;
import com.bootcamp.complementar.ex1.entity.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JsonOrderRepository implements OrderRepository {
    private Map<Long, Order> orders;
    private final ObjectMapper mapper = new ObjectMapper();
    private File file;
    private Long nextId;

    @PostConstruct
    private void loadDatabase() throws IOException {
        this.file = ResourceUtils.getFile("classpath:order.json");

        TypeReference<Map<Long, Order>> type = new TypeReference<>() {};
        this.orders = mapper.readValue(file, type);

        this.nextId = orders.keySet().stream().max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    public Map<Long, Order> all() {
        return orders;
    }

    public Order get(Long id) {
        return orders.get(id);
    }

    public Order create(OrderRequest orderRequest) throws IOException {
        return save(nextId++, orderRequest);
    }

    public Order update(Long id, OrderRequest orderRequest) throws IOException {
        return save(id, orderRequest);
    }

    public Order delete(Long id) throws IOException {
        Order order = orders.remove(id);

        mapper.writeValue(file, orders);

        return order;
    }

    private Order save(Long id, OrderRequest orderRequest) throws IOException {
        Order order = Order.builder()
                .id(id)
                .tableId(orderRequest.getTableId())
                .dishQuantities(orderRequest.getDishQuantities())
                .build();

        orders.put(id, order);

        mapper.writeValue(file, orders);

        return order;
    }
}
