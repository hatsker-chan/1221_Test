package com.example.test1221.core.service;

import com.example.test1221.api.dto.PostMealDto;
import com.example.test1221.core.model.Customer;
import com.example.test1221.core.model.Meal;
import com.example.test1221.core.repository.CustomerRepository;
import com.example.test1221.core.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final CustomerRepository customerRepository;

    public Meal createMeal(PostMealDto mealDto) {
        long customerId = mealDto.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalArgumentException("Cannot find customer with id" + customerId)
        );

        Meal meal = Meal.builder()
                .customer(customer)
                .date(LocalDateTime.now())
                .note(mealDto.getNote())
                .dishes(Set.copyOf(mealDto.getDishes()))
                .build();

        return mealRepository.save(meal);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
