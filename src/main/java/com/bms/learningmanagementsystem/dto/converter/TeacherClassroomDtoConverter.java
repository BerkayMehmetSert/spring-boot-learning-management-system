package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TeacherClassroomDto;
import com.bms.learningmanagementsystem.model.Classroom;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeacherClassroomDtoConverter implements DtoConverter<TeacherClassroomDto, Classroom> {
    @Override
    public TeacherClassroomDto convert(Classroom from) {
        return new TeacherClassroomDto(
                from.getId(),
                from.getTitle(),
                from.getClassNo(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getCoursePerCycle()).getId()
        );
    }
}
