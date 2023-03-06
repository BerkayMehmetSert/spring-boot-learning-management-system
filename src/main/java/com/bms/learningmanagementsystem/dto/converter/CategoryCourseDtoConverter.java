package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.CategoryCourseDto;
import com.bms.learningmanagementsystem.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CategoryCourseDtoConverter implements DtoConverter<CategoryCourseDto, Course> {

    @Override
    public CategoryCourseDto convert(Course from) {
        return new CategoryCourseDto(
                from.getId(),
                from.getCourseNo(),
                from.getDescription(),
                from.getAbstractDescription(),
                from.getBibliography(),
                from.getCreatedDate()
        );
    }
}
