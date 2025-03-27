package com.example._test.core.service;

import com.example._test.api.dto.PostDishDto;
import com.example._test.core.model.Dish;
import com.example._test.core.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public Dish createDish(PostDishDto dishDto) {
        if (checkDish(dishDto.getTitle())) {
            throw new IllegalArgumentException("Dish with title " + dishDto.getTitle() + " already exists");
        }
        Dish dish = Dish.builder()
                        .title(dishDto.getTitle())
                .caloriesPerDish(dishDto.getCalories())
                .protein(dishDto.getProtein())
                .carbohydrates(dishDto.getCarbohydrates())
                .fat(dishDto.getFat())
                .build();

        return dishRepository.save(dish);
    }

    private boolean checkDish(String title) {
        return dishRepository.findByTitle(title).isPresent();
    }
}
