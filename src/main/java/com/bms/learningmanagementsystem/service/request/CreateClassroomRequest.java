package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateClassroomRequest(
        String title,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String teacherId,
        String coursePerCycleId
) {
}
