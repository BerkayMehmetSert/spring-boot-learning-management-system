package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TeacherClassroomDto(
        String id,
        String title,
        String classNo,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate createdDate,
        String coursePerCycleId

) implements Dto {
}
