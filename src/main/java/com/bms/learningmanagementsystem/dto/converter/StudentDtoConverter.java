package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.StudentDto;
import com.bms.learningmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoConverter implements DtoConverter<StudentDto, Student> {
    private final StudentTestScoreDtoConverter testScoreDtoConverter;
    private final StudentEnrollmentDtoConverter enrollmentDtoConverter;
    private final StudentAttendanceDtoConverter attendanceDtoConverter;

    public StudentDtoConverter(StudentTestScoreDtoConverter testScoreDtoConverter,
                               StudentEnrollmentDtoConverter enrollmentDtoConverter,
                               StudentAttendanceDtoConverter attendanceDtoConverter) {
        this.testScoreDtoConverter = testScoreDtoConverter;
        this.enrollmentDtoConverter = enrollmentDtoConverter;
        this.attendanceDtoConverter = attendanceDtoConverter;
    }

    @Override
    public StudentDto convert(Student from) {
        return new StudentDto(
                from.getId(),
                from.getStudentNo(),
                from.getName(),
                from.getEmail(),
                from.getBirthDate(),
                from.getPhoneNo(),
                from.getCreatedDate(),
                from.getTestScores() != null ? testScoreDtoConverter.convert(from.getTestScores()) : null,
                from.getEnrollments() != null ? enrollmentDtoConverter.convert(from.getEnrollments()) : null,
                from.getAttendances() != null ? attendanceDtoConverter.convert(from.getAttendances()) : null
        );
    }
}
