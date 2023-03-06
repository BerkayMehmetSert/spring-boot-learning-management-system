package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record CourseDto(
        String id,
        String courseNo,
        String description,
        String abstractDescription,
        String bibliography,
        LocalDate createdDate,
        String categoryId
) implements Dto {
}
