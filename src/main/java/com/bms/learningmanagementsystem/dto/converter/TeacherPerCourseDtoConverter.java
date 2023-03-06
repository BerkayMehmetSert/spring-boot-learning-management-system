package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TeacherPerCourseDto;
import com.bms.learningmanagementsystem.model.TeacherPerCourse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeacherPerCourseDtoConverter implements DtoConverter<TeacherPerCourseDto, TeacherPerCourse> {
    @Override
    public TeacherPerCourseDto convert(TeacherPerCourse from) {
        return new TeacherPerCourseDto(
                from.getId(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getTeacher()).getId()
        );
    }
}
