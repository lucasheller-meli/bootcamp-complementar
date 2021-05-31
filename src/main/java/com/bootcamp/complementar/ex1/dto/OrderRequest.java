package com.bootcamp.complementar.ex1.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class OrderRequest {
    private Long tableId;
    private Map<Long, Integer> dishQuantities;
}
