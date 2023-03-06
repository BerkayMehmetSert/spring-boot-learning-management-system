package com.bms.learningmanagementsystem.service.request;

public record CreateTeacherPerCourseRequest(
        String teacherId,
        String coursePerCycleId
) {
}
