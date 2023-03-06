package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.ClassroomDto;
import com.bms.learningmanagementsystem.model.Classroom;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClassroomDtoConverter implements DtoConverter<ClassroomDto, Classroom> {

    @Override
    public ClassroomDto convert(Classroom from) {
        return new ClassroomDto(
                from.getId(),
                from.getTitle(),
                from.getClassNo(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getTeacher()).getId(),
                Objects.requireNonNull(from.getCoursePerCycle()).getId()
        );
    }
}
