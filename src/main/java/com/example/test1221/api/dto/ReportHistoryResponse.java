package com.example.test1221.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReportHistoryResponse {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<DailyReportResponse> reportHistory;
}
