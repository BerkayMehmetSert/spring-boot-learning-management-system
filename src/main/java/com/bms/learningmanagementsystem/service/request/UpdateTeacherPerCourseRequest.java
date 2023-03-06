package com.bms.learningmanagementsystem.service.request;

public record UpdateTeacherPerCourseRequest(
        String teacherId,
        String coursePerCycleId
) {
}
