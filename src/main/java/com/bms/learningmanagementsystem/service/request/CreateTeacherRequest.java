package com.bms.learningmanagementsystem.service.request;

public record CreateTeacherRequest(
        String name,
        String email,
        String phoneNo
) {
}
