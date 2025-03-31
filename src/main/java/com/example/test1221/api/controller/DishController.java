package com.example.test1221.api.controller;

import com.example.test1221.api.dto.PostDishDto;
import com.example.test1221.api.exception.ApiExceptionHandling;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiExceptionHandling
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

    @GetMapping
    public ResponseEntity<List<Dish>> getDishes() {
        List<Dish> list = dishService.getAllDishes();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
