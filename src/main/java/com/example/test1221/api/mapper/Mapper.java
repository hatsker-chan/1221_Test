package com.example.test1221.api.mapper;

import com.example.test1221.api.dto.DailyReportResponse;
import com.example.test1221.api.dto.MealResponseDto;
import com.example.test1221.core.model.DailyReport;
import com.example.test1221.core.model.Meal;

import java.util.List;

public class Mapper {

    public static MealResponseDto mapMealEntityToResponseDto(Meal entity) {
        return MealResponseDto.builder()
                .mealId(entity.getMealId())
                .note(entity.getNote())
                .date(entity.getDate())
                .dishes(List.copyOf(entity.getDishes()))
                .build();
    }

    public static DailyReportResponse mapDailyReportEntityToResponseDto(DailyReport entity) {
        return DailyReportResponse.builder()
                .date(entity.getDate())
                .totalMeals(entity.getTotalMeals())
                .meals(entity.getMeals().stream().map(Mapper::mapMealEntityToResponseDto).toList())
                .totalProtein(entity.getTotalProtein())
                .totalCarbohydrate(entity.getTotalCarbohydrate())
                .totalFat(entity.getTotalFat())
                .totalCalories(entity.getTotalCalories())
                .build();
    }
}
