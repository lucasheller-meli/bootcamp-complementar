package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.TableRequest;
import com.bootcamp.complementar.ex1.entity.Order;
import com.bootcamp.complementar.ex1.entity.Table;

import java.io.IOException;
import java.util.Map;

public interface TableRepository {
    Map<Long, Table> all();
    Table get(Long id);
    Table create(TableRequest tableRequest) throws IOException;
    Table update(Long id, TableRequest tableRequest) throws IOException;
    Table delete(Long id) throws IOException;
    void close(Long id) throws IOException;
    void addOrder(Order order) throws IOException;
}
