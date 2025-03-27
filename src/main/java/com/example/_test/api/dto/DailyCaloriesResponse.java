package com.example._test.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DailyCaloriesResponse {
    int dailyCalories;

    float weight;

    float height;

    String gender;
}
