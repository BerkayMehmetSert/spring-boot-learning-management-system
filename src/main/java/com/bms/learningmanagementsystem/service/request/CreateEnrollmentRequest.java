package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;

public record CreateEnrollmentRequest(
        LocalDate date,
        String studentId,
        String coursePerCycleId
) {
}
