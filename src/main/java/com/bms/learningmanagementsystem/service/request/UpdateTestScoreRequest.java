package com.bms.learningmanagementsystem.service.request;

public record UpdateTestScoreRequest(
        Integer score,
        String testId,
        String studentId
) {
}
