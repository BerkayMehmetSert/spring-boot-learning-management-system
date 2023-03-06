package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record StudentAttendanceDto(
        String id,
        LocalTime timeArrival,
        LocalTime timeLeave,
        LocalDate createdDate,
        String classroomId
) implements Dto {
}
