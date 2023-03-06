package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;

public record UpdateCycleRequest(
        String description,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate vacationStartDate,
        LocalDate vacationEndDate
) {
}
