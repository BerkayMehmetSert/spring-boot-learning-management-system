package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record TestTestScoreDto(
        String id,
        Integer score,
        LocalDate createdDate,
        String studentId
) implements Dto {
}
