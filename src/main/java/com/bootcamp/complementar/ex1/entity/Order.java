package com.bootcamp.complementar.ex1.entity;

import java.util.Map;

public class Order {
    private Long id;
    private Long tableId;
    private Map<Long, Integer> dishQuantities;
    private Double totalPrice; // TODO: MUDAR PRA MÉTODO
}
