package com.bms.learningmanagementsystem.core.utilities.helper;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateHelper {
    private DateHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}
