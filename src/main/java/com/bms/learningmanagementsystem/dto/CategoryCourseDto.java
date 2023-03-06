package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record CategoryCourseDto(
        String id,
        String courseNo,
        String description,
        String abstractDescription,
        String bibliography,
        LocalDate createdDate
) implements Dto {
}
