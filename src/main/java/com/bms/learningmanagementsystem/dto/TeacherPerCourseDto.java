package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;

public record TeacherPerCourseDto(
        String id,
        LocalDate createdDate,
        String teacherId
) implements Dto {
}
