package com.bms.learningmanagementsystem.service.request;

import java.time.LocalTime;

public record UpdateAttendanceRequest(
        LocalTime timeArrival,
        LocalTime timeLeave,
        String classroomId,
        String studentId
) {
}
