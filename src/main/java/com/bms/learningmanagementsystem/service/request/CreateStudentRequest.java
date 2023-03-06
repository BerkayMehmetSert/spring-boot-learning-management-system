package com.bms.learningmanagementsystem.service.request;

public record CreateStudentRequest(
        String name,
        String email,
        String birthDate,
        String phoneNo
) {
}
