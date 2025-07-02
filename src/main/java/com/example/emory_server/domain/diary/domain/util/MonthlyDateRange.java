package com.example.emory_server.domain.diary.domain.util;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class MonthlyDateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private MonthlyDateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static MonthlyDateRange of(LocalDate date) {
        LocalDate start = date.withDayOfMonth(1);
        LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
        return new MonthlyDateRange(start, end);
    }

}
