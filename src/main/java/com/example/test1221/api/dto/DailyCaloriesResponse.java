package com.example.test1221.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DailyCaloriesResponse {
    private short age;

    private boolean isMale;

    private float weight;

    private float height;

    private String goal;

    private int dailyCalories;
}
