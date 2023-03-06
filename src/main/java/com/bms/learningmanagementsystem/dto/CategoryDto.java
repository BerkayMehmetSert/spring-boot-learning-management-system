package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.util.List;

public record CategoryDto(
        String id,
        String categoryNo,
        String description,
        LocalDate createdDate,
        List<CategoryCourseDto> courses
) implements Dto {
}
