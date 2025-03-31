package com.example.test1221.api.controller;

import com.example.test1221.api.dto.MealResponseDto;
import com.example.test1221.api.dto.PostMealDto;
import com.example.test1221.api.dto.PostMealDtoWithDate;
import com.example.test1221.api.exception.ApiExceptionHandling;
import com.example.test1221.api.mapper.Mapper;
import com.example.test1221.core.model.Meal;
import com.example.test1221.core.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiExceptionHandling
@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealResponseDto> createMeal(@RequestBody PostMealDto postMealDto) {
        Meal meal = mealService.createMeal(postMealDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Mapper.mapMealEntityToResponseDto(meal)
        );
    }


    //Тестовый эндпоинт для заполнения бд
    @PostMapping("/testWithDate")
    public ResponseEntity<MealResponseDto> createMealInDate(
            @RequestBody PostMealDtoWithDate postMealDtoWithDate) {
        Meal meal = mealService.createMeal(postMealDtoWithDate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        Mapper.mapMealEntityToResponseDto(meal)
                );

    }
//    @GetMapping
//    public ResponseEntity<List<Meal>> getMeals() {
//        return ResponseEntity.ok();
//    }
}