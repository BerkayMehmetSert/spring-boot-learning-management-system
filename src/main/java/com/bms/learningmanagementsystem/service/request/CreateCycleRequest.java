package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;

public record CreateCycleRequest(
        String description,
        LocalDate endDate,
        LocalDate vacationStartDate,
        LocalDate vacationEndDate
) {
}
