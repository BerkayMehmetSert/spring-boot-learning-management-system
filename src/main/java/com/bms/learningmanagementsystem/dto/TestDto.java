package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TestDto(
        String id,
        String testNo,
        LocalDate testDate,
        LocalTime testTime,
        String agenda,
        LocalDate createdDate,
        String enrollmentId,
        List<TestTestScoreDto> testScores
) implements Dto {
}
