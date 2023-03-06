package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record TestScoreDto(
        String id,
        Integer score,
        LocalDate createdDate,
        String testId,
        String studentId
) implements Dto {
}
