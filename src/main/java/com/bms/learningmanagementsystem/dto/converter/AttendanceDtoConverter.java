package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.AttendanceDto;
import com.bms.learningmanagementsystem.model.Attendance;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class AttendanceDtoConverter implements DtoConverter<AttendanceDto, Attendance> {

    @Override
    public AttendanceDto convert(Attendance from) {
        return new AttendanceDto(
                from.getId(),
                from.getTimeArrival(),
                from.getTimeLeave(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getClassroom()).getId(),
                Objects.requireNonNull(from.getStudent()).getId()
        );
    }
}
