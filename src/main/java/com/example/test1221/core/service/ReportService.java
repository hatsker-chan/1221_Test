package com.example.test1221.core.service;

import com.example.test1221.core.model.DailyReport;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.model.Meal;
import com.example.test1221.core.repository.MealRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final MealRepository mealRepository;
    private final CustomerService customerService;

    public DailyReport getDailyReport(long customerId, @Nullable LocalDate dateTime) {
        LocalDate date;
        if (dateTime != null) {
            date = dateTime;
        } else {
            date = LocalDate.now();
        }

        LocalDateTime startTime = date.atStartOfDay();

        LocalDateTime endTime = date.plusDays(1).atStartOfDay();

        List<Meal> meals = mealRepository.findAllByCustomerCustomerIdAndDateBetween(
                customerId, startTime, endTime
        ).orElseThrow(RuntimeException::new);

        int totalCalories = 0;
        int totalProtein = 0;
        int totalCarbs = 0;
        int totalFat = 0;

        for (Meal meal : meals) {
            for (Dish dish : meal.getDishes()) {
                totalCalories += dish.getCaloriesPerDish();
                totalProtein += dish.getProtein();
                totalCarbs += dish.getCarbohydrates();
                totalFat += dish.getFat();
            }
        }

        int caloriesGoal = customerService.getDailyCalories(customerId);

        return new DailyReport(
                customerId,
                date,
                meals,
                totalProtein,
                totalCarbs,
                totalFat,
                totalCalories,
                caloriesGoal

        );
    }
}