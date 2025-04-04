package com.example.test1221.core.service;

import com.example.test1221.api.dto.PostDishDto;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.repository.DishRepository;
import com.example.test1221.core.util.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public Dish createDish(PostDishDto dishDto) {
        if (checkDish(dishDto.getTitle())) {
            throw new ExistenceException("Dish with title \"" + dishDto.getTitle() + "\" already exists");
        }

        Validator.validateDish(dishDto);

        Dish dish = Dish.builder()
                .title(dishDto.getTitle())
                .caloriesPerDish(dishDto.getCalories())
                .protein(dishDto.getProtein())
                .carbohydrates(dishDto.getCarbohydrates())
                .fat(dishDto.getFat())
                .build();

        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    private boolean checkDish(String title) {
        return dishRepository.existsByTitle(title);
    }


}
