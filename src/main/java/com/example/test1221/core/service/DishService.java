package com.example.test1221.core.service;

import com.example.test1221.api.dto.PostDishDto;
import com.example.test1221.core.exception.ExistenceException;
import com.example.test1221.core.exception.ValidationException;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.repository.DishRepository;
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

        validateDish(dishDto);

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

    private void validateDish(PostDishDto dish) {
        if (dish.getCalories() < 0 || dish.getProtein() < 0 || dish.getCarbohydrates() < 0 || dish.getFat() < 0) {
            throw new ValidationException("Amount of cals, protein, carbs and fat cannot be negative");
        }

        if (dish.getTitle() == null || dish.getTitle().isEmpty()) {
            throw new ValidationException("Dish title is empty");
        }

        if (dish.getCalories() < 30 || dish.getCalories() > 1500) {
            throw new ValidationException("Dish calories per dish must be between 30 and 1500");
        }

        float proportion = (float) (dish.getCarbohydrates() * 4 + dish.getProtein() * 4 + dish.getFat() * 9) / dish.getCalories();
        if (Math.abs(1 - proportion) > 0.3) {
            throw new ValidationException("Dish calories must be equal about (max diff is 30%) protein * 4 + carbohydrates * 4 + fats * 9 (e. g. (30 * 4 + 35 * 4 + 15 * 9)/400 = 395/400 <= 0.3");
        }
    }
}
