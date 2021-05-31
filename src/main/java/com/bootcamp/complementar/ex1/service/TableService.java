package com.bootcamp.complementar.ex1.service;

import com.bootcamp.complementar.ex1.dto.TableRequest;
import com.bootcamp.complementar.ex1.dto.TableResponse;
import com.bootcamp.complementar.ex1.entity.Table;
import com.bootcamp.complementar.ex1.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TableService {
    private final TableRepository tableRepository;
    private final OrderService orderService;

    public TableService(TableRepository tableRepository, OrderService orderService) {
        this.tableRepository = tableRepository;
        this.orderService = orderService;
    }

    public Map<Long, TableResponse> all() {
        return tableRepository.all()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> TableResponse.from(entry.getValue(), orderService)));
    }

    public TableResponse get(Long id) {
        return TableResponse.from(tableRepository.get(id), orderService);
    }

    public TableResponse create(TableRequest table) throws IOException {
        return TableResponse.from(tableRepository.create(table), orderService);
    }

    public TableResponse update(Long id, TableRequest order) throws IOException {
        return TableResponse.from(tableRepository.update(id, order), orderService);
    }

    public TableResponse delete(Long id) throws IOException {
        return TableResponse.from(tableRepository.delete(id), orderService);
    }

    public TableResponse close(Long id) throws IOException {
        tableRepository.get(id).orders(orderService).forEach(order -> {
            try {
                orderService.close(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tableRepository.close(id);

        return TableResponse.from(tableRepository.get(id), orderService);
    }
}
