package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.TestDto;
import com.bms.learningmanagementsystem.dto.converter.TestDtoConverter;
import com.bms.learningmanagementsystem.model.Test;
import com.bms.learningmanagementsystem.repositoy.TestRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateTestRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTestRequest;
import com.bms.learningmanagementsystem.service.rules.TestBusinessRules;
import com.bms.learningmanagementsystem.service.validations.TestValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final EnrollmentService enrollmentService;
    private final TestDtoConverter converter;
    private final TestBusinessRules rules;
    private final TestValidator validator;

    public TestService(TestRepository testRepository, EnrollmentService enrollmentService,
                       TestDtoConverter converter, TestBusinessRules rules, TestValidator validator) {
        this.testRepository = testRepository;
        this.enrollmentService = enrollmentService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createTest(final CreateTestRequest request) {
        var enrollment = enrollmentService.getEnrollment(request.enrollmentId());
        BusinessRules.run(() -> {
            rules.checkIfTestDateIsBeforeToday(request.testDate());
            rules.checkIfTestEnrollmentIsCanceled(enrollment);
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.testDate().toString(), ValidationMessages.TEST_DATE_REQUIRED);
            validator.notEmpty(request.testTime().toString(), ValidationMessages.TEST_TIME_REQUIRED);
            validator.notEmpty(request.agenda(), ValidationMessages.TEST_AGENDA_REQUIRED);
            validator.notEmpty(request.enrollmentId(), ValidationMessages.TEST_ENROLLMENT_ID_REQUIRED);
            validator.minCharacters(request.agenda(), 10, ValidationMessages.TEST_AGENDA_MIN_LENGTH);
        });

        var test = new Test(
                NumberGenerator.generateRandomString(10),
                request.testDate(),
                request.testTime(),
                request.agenda(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                enrollment
        );

        testRepository.save(test);
    }

    public void updateTest(final String id, final UpdateTestRequest request) {
        var test = getTest(id);

        BusinessRules.run(() -> {
            rules.checkIfTestEnrollmentIsCanceled(Objects.requireNonNull(test.getEnrollment()));
            rules.checkIfTestDateIsBeforeToday(request.testDate());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.testDate().toString(), ValidationMessages.TEST_DATE_REQUIRED);
            validator.notEmpty(request.testTime().toString(), ValidationMessages.TEST_TIME_REQUIRED);
            validator.notEmpty(request.agenda(), ValidationMessages.TEST_AGENDA_REQUIRED);
            validator.minCharacters(request.agenda(), 10, ValidationMessages.TEST_AGENDA_MIN_LENGTH);
        });

        var updatedTest = new Test(
                test.getId(),
                test.getTestNo(),
                request.testDate(),
                request.testTime(),
                request.agenda(),
                test.getCreatedDate(),
                DateHelper.getCurrentDate(),
                enrollmentService.getEnrollment(request.enrollmentId()),
                test.getTestScores()
        );

        testRepository.save(updatedTest);
    }

    public void deleteTest(final String id) {
        testRepository.delete(getTest(id));
    }

    public TestDto getTestById(final String id) {
        return converter.convert(getTest(id));
    }

    public List<TestDto> getAllTests() {
        var tests = testRepository.findAll();

        BusinessRules.run(() -> rules.checkIfTestListIsEmpty(tests));

        return converter.convert(tests);
    }

    protected Test getTest(final String id) {
        return BusinessRules.run(() -> rules.checkIfTestExists(id));
    }
}
