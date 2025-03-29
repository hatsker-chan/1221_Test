package com.example.test1221.api.controller;

import com.example.test1221.api.dto.DailyReportResponse;
import com.example.test1221.api.dto.ReportHistoryResponse;
import com.example.test1221.api.mapper.Mapper;
import com.example.test1221.core.model.DailyReport;
import com.example.test1221.core.service.ReportService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
    public DailyReportResponse getReport(
            @Nullable @RequestParam(name = "date", required = false) LocalDate date,
            @RequestParam(name = "customerId") Long customerId
    ) {

        DailyReport report = reportService.getDailyReport(customerId, date);
        return Mapper.mapDailyReportEntityToResponseDto(report);
    }

    @GetMapping("/history")
    public ReportHistoryResponse getReportHistory(
            @RequestParam(name = "customerId") Long customerId,
            @Nullable @RequestParam(name = "dateFrom", required = false) LocalDate dateFrom,
            @Nullable @RequestParam(name = "dateTo", required = false) LocalDate dateTo
    ) {
        List<DailyReport> reportHistory = reportService.getReportsHistory(
                customerId, dateFrom, dateTo
        );
        List<DailyReportResponse> reportHistoryResponse = reportHistory.stream().map(Mapper::mapDailyReportEntityToResponseDto).toList();
        return new ReportHistoryResponse(reportHistoryResponse);
    }
}
