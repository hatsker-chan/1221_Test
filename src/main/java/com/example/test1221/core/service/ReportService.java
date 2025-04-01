package com.example.test1221.core.service;

import com.example.test1221.core.exception.EntityNotFoundException;
import com.example.test1221.core.model.*;
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
        LocalDate date = dateTime != null ? dateTime : LocalDate.now();

        LocalDateTime startTime = date.atStartOfDay();

        LocalDateTime endTime = date.plusDays(1).atStartOfDay();

        List<Meal> meals = mealRepository.findAllByCustomerCustomerIdAndDateBetween(
                customerId, startTime, endTime
        ).orElseThrow(() -> new EntityNotFoundException("Can't find meals on date: " + dateTime));

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

        int goal = customerService.getDailyCalories(customerId);
        boolean successInCals = ((double) Math.abs(totalCalories - goal) / goal < 0.1);
        return new DailyReport(
                date,
                meals.size(),
                meals,
                totalProtein,
                totalCarbs,
                totalFat,
                totalCalories,
                goal,
                successInCals
        );
    }

    public ReportHistory getReportsHistory(long customerId, @Nullable LocalDate dateFromArg, @Nullable LocalDate dateToArg) {
        Customer customer = customerService.findCustomerById(customerId);

        LocalDate dateFrom = dateFromArg == null ? customer.getCreated_at() : dateFromArg;

        LocalDate dateTo = dateToArg == null ? LocalDate.now() : dateToArg;

        ReportHistory.ReportHistoryBuilder reportHistoryBuilder = ReportHistory.builder()
                .dateFrom(dateFrom)
                .dateTo(dateTo);

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
        return reportHistoryBuilder.dailyReports(reports).build();
    }
}