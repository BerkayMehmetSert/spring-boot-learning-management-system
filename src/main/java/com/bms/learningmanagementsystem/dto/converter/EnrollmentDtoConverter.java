package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.EnrollmentDto;
import com.bms.learningmanagementsystem.model.Enrollment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnrollmentDtoConverter implements DtoConverter<EnrollmentDto, Enrollment> {
    public EnrollmentDto convert(Enrollment from) {
        return new EnrollmentDto(
                from.getId(),
                from.getDate(),
                from.getCancelled(),
                from.getCancellationReason(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getStudent()).getId()
        );
    }
}
