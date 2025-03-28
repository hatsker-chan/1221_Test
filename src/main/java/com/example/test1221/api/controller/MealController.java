package com.example.test1221.api.controller;

import com.example.test1221.api.dto.PostMealDto;
import com.example.test1221.core.model.Meal;
import com.example.test1221.core.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody PostMealDto postMealDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mealService.createMeal(postMealDto));
    }

//    @GetMapping
//    public ResponseEntity<List<Meal>> getMeals() {
//        return ResponseEntity.ok();
//    }
}