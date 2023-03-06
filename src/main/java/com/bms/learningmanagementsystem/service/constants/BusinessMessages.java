package com.bms.learningmanagementsystem.service.constants;

public class BusinessMessages {
    private BusinessMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_LIST_IS_EMPTY = "Category list is empty";

    public static final String COURSE_ALREADY_EXISTS = "Course already exists";
    public static final String COURSE_NOT_FOUND = "Course not found";
    public static final String COURSE_LIST_IS_EMPTY = "Course list is empty";

    public static final String CYCLE_ALREADY_EXISTS = "Cycle already exists";
    public static final String CYCLE_NOT_FOUND = "Cycle not found";
    public static final String CYCLE_LIST_EMPTY = "Cycle list is empty";
    public static final String CYCLE_ENDS_BEFORE_STARTS = "Cycle end date before start date";
    public static final String CYCLE_END_EQUALS_NOW = "Cycle end date equals now";

    public static final String COURSE_PER_CYCLE_NOT_FOUND = "Course per cycle not found";
    public static final String COURSE_PER_CYCLE_LIST_IS_EMPTY = "Course per cycle list is empty";
    public static final String COURSE_PER_CYCLE_ENDS_BEFORE_IT_STARTS = "Course per cycle ends before it starts";

    public static final String STUDENT_ALREADY_EXISTS = "Student already exists";
    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String STUDENT_LIST_IS_EMPTY = "Student list is empty";

    public static final String TEACHER_ALREADY_EXISTS = "Teacher already exists";
    public static final String TEACHER_NOT_FOUND = "Teacher not found";
    public static final String TEACHER_LIST_IS_EMPTY = "Teacher list is empty";

    public static final String TEACHER_PER_COURSE_NOT_FOUND = "Teacher per course not found";
    public static final String TEACHER_PER_COURSE_LIST_IS_EMPTY = "Teacher per course list is empty";

    public static final String ENROLLMENT_NOT_FOUND = "Enrollment not found";
    public static final String ENROLLMENT_LIST_IS_EMPTY = "Enrollment list is empty";
    public static final String ENROLLMENT_CANCELED = "Enrollment canceled";
    public static final String ENROLLMENT_DATE_IS_BEFORE_TODAY = "Enrollment date is before today";

    public static final String TEST_NOT_FOUND = "Test not found";
    public static final String TEST_LIST_IS_EMPTY = "Test list is empty";
    public static final String TEST_DATE_IS_BEFORE_TODAY = "Test date is before today";
    public static final String TEST_ENROLLMENT_CANCELED = "Test enrollment canceled";

    public static final String TEST_SCORE_NOT_FOUND = "Test score not found";
    public static final String TEST_SCORE_LIST_IS_EMPTY = "Test score list is empty";

    public static final String CLASSROOM_ALREADY_EXISTS = "Classroom already exists";
    public static final String CLASSROOM_NOT_FOUND = "Classroom not found";
    public static final String CLASSROOM_LIST_IS_EMPTY = "Classroom list is empty";
    public static final String CLASSROOM_DATE_IS_BEFORE_TODAY = "Classroom date is before today";
    public static final String CLASSROOM_END_TIME_IS_BEFORE_START_TIME = "Classroom end time is before start time";

    public static final String ATTENDANCE_NOT_FOUND = "Attendance not found";
    public static final String ATTENDANCE_TIME_ARRIVAL_IS_AFTER_TIME_LEAVE = "Attendance time arrival is after time leave";
    public static final String ATTENDANCE_LIST_IS_EMPTY = "Attendance list is empty";
}
