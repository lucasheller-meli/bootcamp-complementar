package com.bootcamp.complementar.ex1.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TableRequest {
    private List<Long> orderIds;
}
