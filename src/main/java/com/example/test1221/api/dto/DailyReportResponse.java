package com.example.test1221.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class DailyReportResponse {
    private LocalDate date;
    private int totalMeals;
    private List<MealResponseDto> meals;

    private int totalProtein;
    private int totalCarbohydrate;
    private int totalFat;

    private int totalCalories;
    private int caloriesGoal;

}

