package com.bootcamp.complementar.ex1.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Dish {
    private final Long id;
    private final Double price;
    private final String description;
}
