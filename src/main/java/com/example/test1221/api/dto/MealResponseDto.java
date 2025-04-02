package com.example.test1221.api.dto;

import com.example.test1221.core.model.Dish;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class MealResponseDto {
    private Long mealId;
    private LocalDateTime date;
    private String note;
    private List<Dish> dishes;
}
