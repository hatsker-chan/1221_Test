package com.example.test1221.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DailyReport {

    private LocalDate date;
    private int totalMeals;
    private List<Meal> meals;

    private int totalProtein;
    private int totalCarbohydrate;
    private int totalFat;

    private int totalCalories;

    private int goalCalories;
    private boolean successInCalories;
}
