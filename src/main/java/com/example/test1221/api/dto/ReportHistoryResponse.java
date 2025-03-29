package com.example.test1221.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReportHistoryResponse {
    private List<DailyReportResponse> reportHistory;
}
