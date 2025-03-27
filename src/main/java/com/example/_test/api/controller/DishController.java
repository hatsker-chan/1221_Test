package com.example._test.api.controller;

import com.example._test.api.dto.PostDishDto;
import com.example._test.core.model.Dish;
import com.example._test.core.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody PostDishDto dishDto) {
        Dish dish = dishService.createDish(dishDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dish);
    }

}
