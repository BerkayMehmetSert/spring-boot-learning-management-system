package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.StudentAttendanceDto;
import com.bms.learningmanagementsystem.model.Attendance;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentAttendanceDtoConverter implements DtoConverter<StudentAttendanceDto, Attendance> {

    @Override
    public StudentAttendanceDto convert(Attendance from) {
        return new StudentAttendanceDto(
                from.getId(),
                from.getTimeArrival(),
                from.getTimeLeave(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getClassroom()).getId()
        );
    }
}
