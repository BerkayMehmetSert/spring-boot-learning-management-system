package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.StudentEnrollmentDto;
import com.bms.learningmanagementsystem.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class StudentEnrollmentDtoConverter implements DtoConverter<StudentEnrollmentDto, Enrollment> {
    @Override
    public StudentEnrollmentDto convert(Enrollment from) {
        return new StudentEnrollmentDto(
                from.getId(),
                from.getDate(),
                from.getCancelled(),
                from.getCancellationReason(),
                from.getCreatedDate()
        );
    }
}
