package com.bms.learningmanagementsystem.core.utilities.validations;


import com.bms.learningmanagementsystem.core.exceptions.ValidationException;

public class ValidationService {
    public void notEmpty(String value, String message) {
        if (value == null || value.isEmpty() || value.isBlank())
            throw new ValidationException(message);
    }

    public void maxLength(Number value, Number maxLength, String message) {
        if (value.doubleValue() > maxLength.doubleValue())
            throw new ValidationException(message);
    }

    public void maxCharacters(String value, Number maxLength, String message) {
        if (value.length() > maxLength.doubleValue())
            throw new ValidationException(message);
    }

    public void minLength(Number value, Number minLength, String message) {
        if (value.doubleValue() < minLength.doubleValue())
            throw new ValidationException(message);
    }

    public void minCharacters(String value, Number minLength, String message) {
        if (value.length() < minLength.doubleValue())
            throw new ValidationException(message);
    }

    public void charactersBetween(String value, Number minLength, Number maxLength, String message) {
        if (value.length() < minLength.doubleValue() || value.length() > maxLength.doubleValue())
            throw new ValidationException(message);
    }

    public void greaterThan(Number value, Number min, String message) {
        if (value.doubleValue() < min.doubleValue())
            throw new ValidationException(message);
    }

    public void lessThan(Number value, Number max, String message) {
        if (value.doubleValue() > max.doubleValue())
            throw new ValidationException(message);
    }

    public void greaterThanOrEqual(Number value, Number min, String message) {
        if (value.doubleValue() <= min.doubleValue())
            throw new ValidationException(message);
    }

    public void lessThanOrEqual(Number value, Number max, String message) {
        if (value.doubleValue() >= max.doubleValue())
            throw new ValidationException(message);
    }

    public void email(String email, String regex, String message) {
        if (!email.matches(regex))
            throw new ValidationException(message);
    }
}
