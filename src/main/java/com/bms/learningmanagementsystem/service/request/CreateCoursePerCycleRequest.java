package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;

public record CreateCoursePerCycleRequest(
        LocalDate startDate,
        LocalDate endDate,
        String courseId,
        String cycleId
) {
}
