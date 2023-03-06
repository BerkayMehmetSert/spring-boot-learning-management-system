package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record CycleDto(
        String id,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate vacationStartDate,
        LocalDate vacationEndDate,
        LocalDate createdDate
) implements Dto {
}
