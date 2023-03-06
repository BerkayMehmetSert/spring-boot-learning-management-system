package com.bms.learningmanagementsystem.service.request;

public record CreateTestScoreRequest(
        Integer score,
        String testId,
        String studentId
) {
}
