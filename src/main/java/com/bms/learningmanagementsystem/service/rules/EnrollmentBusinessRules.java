package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.model.Enrollment;
import com.bms.learningmanagementsystem.repositoy.EnrollmentRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EnrollmentBusinessRules {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentBusinessRules(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void checkIfEnrollmentCanceled(String enrollmentId) {
        var enrollment = checkIfEnrollmentExists(enrollmentId);
        if (enrollment.getCancelled()) {
            throw new BusinessException(BusinessMessages.ENROLLMENT_CANCELED);
        }
    }

    public void checkIfEnrollmentDateIsBeforeToday(LocalDate date){
        if (date.isBefore(DateHelper.getCurrentDate())){
            throw new BusinessException(BusinessMessages.ENROLLMENT_DATE_IS_BEFORE_TODAY);
        }
    }
    public Enrollment checkIfEnrollmentExists(String id){
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.ENROLLMENT_NOT_FOUND));
    }

    public void checkIfEnrollmentListIsEmpty(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            throw new NotFoundException(BusinessMessages.ENROLLMENT_LIST_IS_EMPTY);
        }
    }
}
