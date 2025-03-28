package com.example.test1221.api.controller;

import com.example.test1221.core.model.DailyReport;
import com.example.test1221.core.service.ReportService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
//    отчет за день с суммой всех калорий и приемов пищи;
//
//    проверка, уложился ли пользователь в свою дневную норму калорий;
//
//    история питания по дням.

    private final ReportService reportService;

    @GetMapping("/daily")
    public DailyReport getReport(
            @Nullable @RequestParam(required = false) LocalDate date,
            @RequestParam Long customerId
    ) {
        return reportService.getDailyReport(customerId, date);
    }
}
