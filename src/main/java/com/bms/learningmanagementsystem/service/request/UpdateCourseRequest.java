package com.bms.learningmanagementsystem.service.request;

public record UpdateCourseRequest(
        String description,
        String abstractDescription,
        String bibliography,
        String categoryId
) {
}
