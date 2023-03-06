package com.bms.learningmanagementsystem.dto;

import com.bms.learningmanagementsystem.core.dto.Dto;

import java.time.LocalDate;
import java.util.List;

public record StudentDto(
        String id,
        String studentNo,
        String name,
        String email,
        String birthDate,
        String phoneNo,
        LocalDate createdDate,
        List<StudentTestScoreDto> testScores,
        List<StudentEnrollmentDto> enrollments,
        List<StudentAttendanceDto> attendances
) implements Dto {
}
