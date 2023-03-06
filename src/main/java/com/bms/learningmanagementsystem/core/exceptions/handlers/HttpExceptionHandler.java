package com.bms.learningmanagementsystem.core.exceptions.handlers;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.core.exceptions.ValidationException;
import com.bms.learningmanagementsystem.core.exceptions.problemDetails.BusinessProblemDetails;
import com.bms.learningmanagementsystem.core.exceptions.problemDetails.InternalServerProblemDetails;
import com.bms.learningmanagementsystem.core.exceptions.problemDetails.NotFoundProblemDetails;
import com.bms.learningmanagementsystem.core.exceptions.problemDetails.ValidationProblemDetails;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler extends BaseExceptionHandler {
    @Override
    @ExceptionHandler(BusinessException.class)
    protected ProblemDetail handleException(BusinessException exception) {
        return new BusinessProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(NotFoundException.class)
    protected ProblemDetail handleException(NotFoundException exception) {
        return new NotFoundProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(Exception.class)
    protected ProblemDetail handleException(Exception exception) {
        return new InternalServerProblemDetails(exception.getMessage());
    }

    @Override
    @ExceptionHandler(ValidationException.class)
    protected ProblemDetail handleException(ValidationException exception) {
        return new ValidationProblemDetails(exception.getMessage());
    }
}
