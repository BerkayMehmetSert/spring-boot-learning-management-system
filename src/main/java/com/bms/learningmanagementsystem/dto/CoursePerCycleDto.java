package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record CoursePerCycleDto(
        String id,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate createdDate,
        String courseId,
        String cycleId
) implements Dto {
}
