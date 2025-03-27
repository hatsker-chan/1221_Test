package com.example._test.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostDishDto {
    private String title;

    private int calories;

    private int protein;

    private int carbohydrates;

    private int fat;
}
