package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.util.List;

public record TeacherDto(
        String id,
        String teacherNo,
        String name,
        String email,
        String phoneNo,
        LocalDate createdDate,
        List<TeacherClassroomDto> classrooms
) implements Dto {
}
