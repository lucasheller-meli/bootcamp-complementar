package com.bootcamp.complementar.ex1.controller;

import com.bootcamp.complementar.ex1.dto.TableRequest;
import com.bootcamp.complementar.ex1.dto.TableResponse;
import com.bootcamp.complementar.ex1.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/tables")
public class TableController {
    public final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<Map<Long, TableResponse>> all() {
        return ResponseEntity.ok(tableService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.get(id));
    }

    @PostMapping
    public ResponseEntity<TableResponse> create(@RequestBody TableRequest table) throws IOException {
        TableResponse savedOrder = tableService.create(table);

        return ResponseEntity.created(URI.create(String.format("/tables/%s", savedOrder.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableResponse> update(@PathVariable Long id, @RequestBody TableRequest table) throws IOException {
        return ResponseEntity.ok(tableService.update(id, table));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TableResponse> delete(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(tableService.delete(id));
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<TableResponse> close(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(tableService.close(id));
    }
}
