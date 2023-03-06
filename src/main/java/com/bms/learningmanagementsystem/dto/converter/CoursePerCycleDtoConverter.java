package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.CoursePerCycleDto;
import com.bms.learningmanagementsystem.model.CoursePerCycle;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CoursePerCycleDtoConverter implements DtoConverter<CoursePerCycleDto, CoursePerCycle> {

    @Override
    public CoursePerCycleDto convert(CoursePerCycle from) {
        return new CoursePerCycleDto(
                from.getId(),
                from.getStartDate(),
                from.getEndDate(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getCourse()).getId(),
                Objects.requireNonNull(from.getCycle()).getId()
        );
    }
}
