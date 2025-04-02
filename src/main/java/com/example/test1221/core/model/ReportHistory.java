package com.example.test1221.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ReportHistory {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<DailyReport> dailyReports;
}
