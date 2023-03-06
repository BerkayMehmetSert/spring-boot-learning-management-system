package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Attendance;
import com.bms.learningmanagementsystem.repositoy.AttendanceRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class AttendanceBusinessRules {
    private final AttendanceRepository attendanceRepository;

    public AttendanceBusinessRules(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance checkIfAttendanceExists(String id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.ATTENDANCE_NOT_FOUND));
    }

    public void checkIfAttendanceTimeArrivalIsBeforeTimeLeave(LocalTime timeArrival, LocalTime timeLeave) {
        if (timeArrival.isAfter(timeLeave)) {
            throw new BusinessException(BusinessMessages.ATTENDANCE_TIME_ARRIVAL_IS_AFTER_TIME_LEAVE);
        }
    }

    public void checkIfAttendanceListIsEmpty(List<Attendance> attendanceList) {
        if (attendanceList.isEmpty()) {
            throw new NotFoundException(BusinessMessages.ATTENDANCE_LIST_IS_EMPTY);
        }
    }
}
