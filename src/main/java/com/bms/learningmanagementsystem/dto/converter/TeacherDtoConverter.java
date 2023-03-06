package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TeacherDto;
import com.bms.learningmanagementsystem.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherDtoConverter implements DtoConverter<TeacherDto, Teacher> {
    private final TeacherClassroomDtoConverter classroomDtoConverter;

    public TeacherDtoConverter(TeacherClassroomDtoConverter classroomDtoConverter) {
        this.classroomDtoConverter = classroomDtoConverter;
    }

    @Override
    public TeacherDto convert(Teacher from) {
        return new TeacherDto(
                from.getId(),
                from.getTeacherNo(),
                from.getName(),
                from.getEmail(),
                from.getPhoneNo(),
                from.getCreatedDate(),
                from.getClassrooms() != null ? classroomDtoConverter.convert(from.getClassrooms()) : null
        );
    }
}
