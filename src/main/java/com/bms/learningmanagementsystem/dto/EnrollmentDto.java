package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record EnrollmentDto(
        String id,
        LocalDate date,
        Boolean cancelled,
        String cancellationReason,
        LocalDate createdDate,
        String studentId
) implements Dto {
}
