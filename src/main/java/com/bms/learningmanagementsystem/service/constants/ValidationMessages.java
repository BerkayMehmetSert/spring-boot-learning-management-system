package com.bms.learningmanagementsystem.service.constants;

public class ValidationMessages {
    private ValidationMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static final String CATEGORY_DESCRIPTION_REQUIRED = "Category description is required";
    public static final String CATEGORY_DESCRIPTION_LENGTH = "Category description must be between 3 and 50 characters";

    public static final String COURSE_DESCRIPTION_REQUIRED = "Course description is required";
    public static final String COURSE_ABSTRACT_DESCRIPTION_REQUIRED = "Course abstract description is required";
    public static final String COURSE_BIBLIOGRAPHY_REQUIRED = "Course bibliography is required";
    public static final String COURSE_CATEGORY_ID_REQUIRED = "Course category id is required";
    public static final String COURSE_DESCRIPTION_MIN_LENGTH = "Course description must be at least 3 characters";
    public static final String COURSE_ABSTRACT_DESCRIPTION_MIN_LENGTH = "Course abstract description must be at least 3 characters";
    public static final String COURSE_BIBLIOGRAPHY_MIN_LENGTH = "Course bibliography must be at least 3 characters";

    public static final String CYCLE_DESCRIPTION_REQUIRED = "Cycle description is required";
    public static final String CYCLE_END_DATE_REQUIRED = "Cycle end date is required";
    public static final String CYCLE_VACATION_START_DATE_REQUIRED = "Cycle vacation start date is required";
    public static final String CYCLE_VACATION_END_DATE_REQUIRED = "Cycle vacation end date is required";

    public static final String COURSE_PER_CYCLE_START_DATE_REQUIRED = "Course per cycle start date is required";
    public static final String COURSE_PER_CYCLE_END_DATE_REQUIRED = "Course per cycle end date is required";
    public static final String COURSE_PER_CYCLE_COURSE_ID_REQUIRED = "Course per cycle course id is required";
    public static final String COURSE_PER_CYCLE_CYCLE_ID_REQUIRED = "Course per cycle cycle id is required";

    public static final String STUDENT_NAME_REQUIRED = "Student name is required";
    public static final String STUDENT_EMAIL_REQUIRED = "Student email is required";
    public static final String STUDENT_BIRTH_DATE_REQUIRED = "Student birth date is required";
    public static final String STUDENT_PHONE_NO_REQUIRED = "Student phone no is required";
    public static final String STUDENT_NAME_MIN_CHARACTERS = "Student name must be at least 3 characters";
    public static final String STUDENT_EMAIL_INVALID = "Student email is invalid";

    public static final String TEACHER_NAME_REQUIRED = "Teacher name is required";
    public static final String TEACHER_EMAIL_REQUIRED = "Teacher email is required";
    public static final String TEACHER_PHONE_REQUIRED = "Teacher phone no is required";
    public static final String TEACHER_NAME_MIN_LENGTH = "Teacher name must be at least 3 characters";
    public static final String TEACHER_EMAIL_INVALID = "Teacher email is invalid";

    public static final String TEACHER_ID_REQUIRED = "Teacher per course teacher id is required";
    public static final String COURSE_PER_CYCLE_ID_REQUIRED = "Teacher per course course per cycle id is required";

    public static final String ENROLLMENT_DATE_REQUIRED = "Enrollment date is required";
    public static final String STUDENT_ID_REQUIRED = "Enrollment student id is required";
    public static final String CANCELLATION_REASON_REQUIRED = "Enrollment cancellation reason is required";

    public static final String TEST_DATE_REQUIRED = "Test date is required";
    public static final String TEST_TIME_REQUIRED = "Test time is required";
    public static final String TEST_AGENDA_REQUIRED = "Test agenda is required";
    public static final String TEST_AGENDA_MIN_LENGTH = "Test agenda must be at least 10 characters";
    public static final String TEST_ENROLLMENT_ID_REQUIRED = "Test enrollment id is required";

    public static final String SCORE_IS_REQUIRED = "Test score is required";
    public static final String TEST_ID_IS_REQUIRED = "Test id is required";
    public static final String STUDENT_ID_IS_REQUIRED = "Student id is required";
    public static final String SCORE_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO = "Score must be greater than or equal to zero";

    public static final String CLASSROOM_TITLE_REQUIRED = "Classroom title is required";
    public static final String CLASSROOM_DATE_REQUIRED = "Classroom date is required";
    public static final String CLASSROOM_START_TIME_REQUIRED = "Classroom start time is required";
    public static final String CLASSROOM_END_TIME_REQUIRED = "Classroom end time is required";
    public static final String CLASSROOM_TEACHER_ID_REQUIRED = "Classroom teacher id is required";
    public static final String CLASSROOM_COURSE_PER_CYCLE_ID_REQUIRED = "Classroom course per cycle id is required";

    public static final String ATTENDANCE_TIME_ARRIVAL_IS_REQUIRED = "Attendance time arrival is required";
    public static final String ATTENDANCE_TIME_LEAVE_IS_REQUIRED = "Attendance time leave is required";
    public static final String ATTENDANCE_CLASSROOM_ID_IS_REQUIRED = "Attendance classroom id is required";
    public static final String ATTENDANCE_STUDENT_ID_IS_REQUIRED = "Attendance student id is required";
}
