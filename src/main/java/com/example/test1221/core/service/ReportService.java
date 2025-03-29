package com.example.test1221.core.service;

import com.example.test1221.core.model.Customer;
import com.example.test1221.core.model.DailyReport;
import com.example.test1221.core.model.Dish;
import com.example.test1221.core.model.Meal;
import com.example.test1221.core.repository.MealRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                date,
                meals.size(),
                meals,
                totalProtein,
                totalCarbs,
                totalFat,
                totalCalories,
                caloriesGoal

        );
    }

    public List<DailyReport> getReportsHistory(long customerId, @Nullable LocalDate dateFrom, @Nullable LocalDate dateTo) {
        Customer customer = customerService.findCustomerById(customerId);

        dateFrom = dateFrom == null ? customer.getCreated_at() : dateFrom;

        dateTo = dateTo == null ? LocalDate.now() : dateTo;

        ArrayList<DailyReport> reports = new ArrayList<>();
        while (dateFrom.isBefore(dateTo)) {
            DailyReport dailyReport = getDailyReport(customerId, dateFrom);
            if (dailyReport.getTotalMeals() == 0) {
                dateFrom = dateFrom.plusDays(1);
                continue;
            }
            reports.add(dailyReport);
            dateFrom = dateFrom.plusDays(1);
        }
        return reports;
    }
}