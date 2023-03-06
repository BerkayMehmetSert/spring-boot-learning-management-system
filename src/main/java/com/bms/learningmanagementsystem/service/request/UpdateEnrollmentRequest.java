package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;

public record UpdateEnrollmentRequest(
        LocalDate date,
        String studentId,
        String coursePerCycleId
) {
}
