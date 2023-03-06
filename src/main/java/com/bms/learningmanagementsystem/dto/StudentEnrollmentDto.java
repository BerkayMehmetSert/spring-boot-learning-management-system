package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record StudentEnrollmentDto(
        String id,
        LocalDate date,
        Boolean cancelled,
        String cancellationReason,
        LocalDate createdDate
) implements Dto {
}
