package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.model.Enrollment;
import com.bms.learningmanagementsystem.model.Test;
import com.bms.learningmanagementsystem.repositoy.TestRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TestBusinessRules {
    private final TestRepository testRepository;

    public TestBusinessRules(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void checkIfTestDateIsBeforeToday(LocalDate date) {
        if (date.isBefore(DateHelper.getCurrentDate())) {
            throw new BusinessException(BusinessMessages.TEST_DATE_IS_BEFORE_TODAY);
        }
    }

    public void checkIfTestEnrollmentIsCanceled(Enrollment enrollment) {
        if (enrollment.getCancelled()) {
            throw new NotFoundException(BusinessMessages.TEST_ENROLLMENT_CANCELED);
        }
    }

    public Test checkIfTestExists(String id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.TEST_NOT_FOUND));
    }

    public void checkIfTestListIsEmpty(List<Test> tests) {
        if (tests.isEmpty()) {
            throw new NotFoundException(BusinessMessages.TEST_LIST_IS_EMPTY);
        }
    }
}
