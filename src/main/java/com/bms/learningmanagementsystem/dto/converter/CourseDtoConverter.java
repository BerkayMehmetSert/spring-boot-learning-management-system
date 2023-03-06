package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.CourseDto;
import com.bms.learningmanagementsystem.model.Course;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseDtoConverter implements DtoConverter<CourseDto, Course> {

    @Override
    public CourseDto convert(Course from) {
        return new CourseDto(
                from.getId(),
                from.getCourseNo(),
                from.getDescription(),
                from.getAbstractDescription(),
                from.getBibliography(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getCategory()).getId()
        );
    }
}
