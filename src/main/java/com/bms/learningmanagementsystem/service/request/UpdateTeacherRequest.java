package com.bms.learningmanagementsystem.service.request;

public record UpdateTeacherRequest(
        String name,
        String email,
        String phoneNo
) {
}
