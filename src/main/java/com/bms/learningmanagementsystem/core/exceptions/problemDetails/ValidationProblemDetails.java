package com.bms.learningmanagementsystem.core.exceptions.problemDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValidationProblemDetails extends ProblemDetail {
    public ValidationProblemDetails(String detail) {
        setTitle("Validation Exception");
        setDetail(detail);
        setStatus(HttpStatus.BAD_REQUEST);
        setType(URI.create("https://example.com/validation-exception"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
