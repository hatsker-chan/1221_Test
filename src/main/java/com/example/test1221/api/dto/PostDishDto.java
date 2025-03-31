package com.example.test1221.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDishDto {
    private String title;

    private int calories;

    private int protein;

    private int carbohydrates;

    private int fat;
}
