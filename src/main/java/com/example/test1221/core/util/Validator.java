package com.example.test1221.core.util;

import com.example.test1221.api.dto.PostCustomerDto;
import com.example.test1221.api.dto.PostDishDto;
import com.example.test1221.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {

    public static void validateCustomer(PostCustomerDto customer) {
        if (!Pattern.compile("^(.+)@(\\S+)$").matcher(customer.getEmail()).matches()) {
            throw new ValidationException("Email is not valid: " + customer.getEmail());
        }
        if (customer.getAge() < 12 || customer.getAge() > 100) {
            throw new ValidationException("Age must be between 12 and 100");
        }
        if (customer.getHeight() < 120 || customer.getHeight() > 230) {
            throw new ValidationException("Height must be between 120 and 230");
        }
        if (customer.getWeight() < 30 || customer.getWeight() > 200) {
            throw new ValidationException("Weight must be between 30 and 200");
        }
    }

    public static void validateDish(PostDishDto dish) {
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
