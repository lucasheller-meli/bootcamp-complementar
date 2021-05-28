package com.bootcamp.complementar.ex1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long id;
    private Double price;
    private String description;
}
