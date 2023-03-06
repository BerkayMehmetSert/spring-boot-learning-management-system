package com.bms.learningmanagementsystem.service.request;

public record CreateCourseRequest(
        String description,
        String abstractDescription,
        String bibliography,
        String categoryId
) {
}
