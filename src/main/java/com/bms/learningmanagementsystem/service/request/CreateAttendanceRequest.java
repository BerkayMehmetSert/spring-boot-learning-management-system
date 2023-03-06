package com.bms.learningmanagementsystem.service.request;

import java.time.LocalTime;

public record CreateAttendanceRequest(
        LocalTime timeArrival,
        LocalTime timeLeave,
        String classroomId,
        String studentId
) {
}
