package com.bootcamp.complementar.ex1.repository;

import com.bootcamp.complementar.ex1.dto.TableRequest;
import com.bootcamp.complementar.ex1.entity.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

@Repository
public class JsonTableRepository implements TableRepository {
    private Map<Long, Table> tables;
    private final ObjectMapper mapper = new ObjectMapper();
    private File file;
    private Long nextId;

    @PostConstruct
    private void loadDatabase() throws IOException {
        this.file = ResourceUtils.getFile("classpath:table.json");

        TypeReference<Map<Long, Table>> type = new TypeReference<>() {};
        this.tables = mapper.readValue(file, type);

        this.nextId = tables.keySet().stream().max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    public Map<Long, Table> all() {
        return tables;
    }

    public Table get(Long id) {
        return tables.get(id);
    }

    public Table create(TableRequest tableRequest) throws IOException {
        return save(nextId++, tableRequest);
    }

    public Table update(Long id, TableRequest tableRequest) throws IOException {
        return save(id, tableRequest);
    }

    public Table delete(Long id) throws IOException {
        Table table = tables.remove(id);

        mapper.writeValue(file, tables);

        return table;
    }

    private Table save(Long id, TableRequest tableRequest) throws IOException {
        Table table = Table.builder()
                .id(id)
                .orderIds(tableRequest.getOrderIds())
                .build();

        tables.put(id, table);

        mapper.writeValue(file, tables);

        return table;
    }
}
