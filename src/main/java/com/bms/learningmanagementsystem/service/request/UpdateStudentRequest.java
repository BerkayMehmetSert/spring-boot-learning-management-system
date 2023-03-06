package com.bms.learningmanagementsystem.service.request;

public record UpdateStudentRequest(
        String name,
        String email,
        String birthDate,
        String phoneNo
) {
}
