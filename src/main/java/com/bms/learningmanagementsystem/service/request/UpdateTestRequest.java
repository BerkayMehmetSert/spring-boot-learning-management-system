package com.bms.learningmanagementsystem.service.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateTestRequest(
        LocalDate testDate,
        LocalTime testTime,
        String agenda,
        String enrollmentId
) {
}
