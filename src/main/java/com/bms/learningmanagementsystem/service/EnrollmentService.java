package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.EnrollmentDto;
import com.bms.learningmanagementsystem.dto.converter.EnrollmentDtoConverter;
import com.bms.learningmanagementsystem.model.Enrollment;
import com.bms.learningmanagementsystem.repositoy.EnrollmentRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateEnrollmentRequest;
import com.bms.learningmanagementsystem.service.request.UpdateEnrollmentRequest;
import com.bms.learningmanagementsystem.service.rules.EnrollmentBusinessRules;
import com.bms.learningmanagementsystem.service.validations.EnrollmentValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CoursePerCycleService coursePerCycleService;
    private final EnrollmentDtoConverter converter;
    private final EnrollmentBusinessRules rules;
    private final EnrollmentValidator validator;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentService studentService,
                             CoursePerCycleService coursePerCycleService, EnrollmentDtoConverter converter,
                             EnrollmentBusinessRules rules, EnrollmentValidator validator) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.coursePerCycleService = coursePerCycleService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createEnrollment(final CreateEnrollmentRequest request) {
        BusinessRules.run(() -> rules.checkIfEnrollmentDateIsBeforeToday(request.date()));

        ValidationRules.run(() -> {
            validator.notEmpty(request.date().toString(), ValidationMessages.ENROLLMENT_DATE_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.STUDENT_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.COURSE_PER_CYCLE_ID_REQUIRED);
        });

        var enrollment = new Enrollment(
                request.date(),
                Boolean.FALSE,
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                studentService.getStudent(request.studentId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId())
        );

        enrollmentRepository.save(enrollment);
    }

    public void updateEnrollment(final String id, final UpdateEnrollmentRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfEnrollmentCanceled(id);
            rules.checkIfEnrollmentDateIsBeforeToday(request.date());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.date().toString(), ValidationMessages.ENROLLMENT_DATE_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.STUDENT_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.COURSE_PER_CYCLE_ID_REQUIRED);
        });

        var enrollment = getEnrollment(id);

        var updatedEnrollment = new Enrollment(
                enrollment.getId(),
                request.date(),
                enrollment.getCancelled(),
                enrollment.getCancellationReason(),
                enrollment.getCreatedDate(),
                DateHelper.getCurrentDate(),
                studentService.getStudent(request.studentId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId()),
                enrollment.getTests()
        );

        enrollmentRepository.save(updatedEnrollment);
    }

    public void cancelEnrollment(final String id, final String cancellationReason) {
        BusinessRules.run(() -> rules.checkIfEnrollmentCanceled(id));

        ValidationRules.run(() -> {
            validator.notEmpty(cancellationReason, ValidationMessages.CANCELLATION_REASON_REQUIRED);
        });

        var enrollment = getEnrollment(id);

        var updatedEnrollment = new Enrollment(
                enrollment.getId(),
                enrollment.getDate(),
                Boolean.TRUE,
                cancellationReason,
                enrollment.getCreatedDate(),
                DateHelper.getCurrentDate(),
                enrollment.getStudent(),
                enrollment.getCoursePerCycle(),
                enrollment.getTests()
        );

        enrollmentRepository.save(updatedEnrollment);
    }

    public void deleteEnrollment(final String id) {
        BusinessRules.run(() -> rules.checkIfEnrollmentCanceled(id));

        enrollmentRepository.delete(getEnrollment(id));
    }

    public EnrollmentDto getEnrollmentById(final String id) {
        return converter.convert(getEnrollment(id));
    }

    public List<EnrollmentDto> getAllEnrollments() {
        var enrollments = enrollmentRepository.findAll();

        BusinessRules.run(() -> rules.checkIfEnrollmentListIsEmpty(enrollments));

        return converter.convert(enrollments);
    }

    protected Enrollment getEnrollment(String id) {
        return BusinessRules.run(() -> rules.checkIfEnrollmentExists(id));
    }
}
