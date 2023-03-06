package com.bms.learningmanagementsystem.core.exceptions.problemDetails;

import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternalServerProblemDetails extends ProblemDetail {
    public InternalServerProblemDetails(String detail) {
        setTitle("Internal Server Exception");
        setDetail(detail);
        setStatus(500);
        setType(URI.create("https://example.com/internal-server-exception"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
