package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.TestScoreDto;
import com.bms.learningmanagementsystem.dto.converter.TestScoreDtoConverter;
import com.bms.learningmanagementsystem.model.TestScore;
import com.bms.learningmanagementsystem.repositoy.TestScoreRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateTestScoreRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTestScoreRequest;
import com.bms.learningmanagementsystem.service.rules.TestScoreBusinessRules;
import com.bms.learningmanagementsystem.service.validations.TestScoreValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestScoreService {
    private final TestScoreRepository testScoreRepository;
    private final TestService testService;
    private final StudentService studentService;
    private final TestScoreDtoConverter converter;
    private final TestScoreBusinessRules rules;
    private final TestScoreValidator validator;

    public TestScoreService(TestScoreRepository testScoreRepository, TestService testService,
                            StudentService studentService, TestScoreDtoConverter converter,
                            TestScoreBusinessRules rules, TestScoreValidator validator) {
        this.testScoreRepository = testScoreRepository;
        this.testService = testService;
        this.studentService = studentService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createTestScore(final CreateTestScoreRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.score().toString(), ValidationMessages.SCORE_IS_REQUIRED);
            validator.notEmpty(request.testId(), ValidationMessages.TEST_ID_IS_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.STUDENT_ID_IS_REQUIRED);
            validator.greaterThanOrEqual(request.score(), 0, ValidationMessages.SCORE_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
        });

        var testScore = new TestScore(
                request.score(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                testService.getTest(request.testId()),
                studentService.getStudent(request.studentId())
        );

        testScoreRepository.save(testScore);
    }

    public void updateTestScore(final String id, final UpdateTestScoreRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.score().toString(), ValidationMessages.SCORE_IS_REQUIRED);
            validator.notEmpty(request.testId(), ValidationMessages.TEST_ID_IS_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.STUDENT_ID_IS_REQUIRED);
            validator.greaterThanOrEqual(request.score(), 0, ValidationMessages.SCORE_MUST_BE_GREATER_THAN_OR_EQUAL_TO_ZERO);
        });

        var testScore = getTestScore(id);

        var updatedTestScore = new TestScore(
                testScore.getId(),
                request.score(),
                testScore.getCreatedDate(),
                DateHelper.getCurrentDate(),
                testService.getTest(request.testId()),
                studentService.getStudent(request.studentId())
        );

        testScoreRepository.save(updatedTestScore);
    }

    public void deleteTestScore(final String id) {
        testScoreRepository.delete(getTestScore(id));
    }

    public TestScoreDto getTestScoreById(final String id) {
        return converter.convert(getTestScore(id));
    }

    public List<TestScoreDto> getAllTestScores() {
        var testScores = testScoreRepository.findAll();

        rules.checkIfTestScoreListIsEmpty(testScores);

        return converter.convert(testScores);
    }

    protected TestScore getTestScore(String id) {
        return BusinessRules.run(() -> rules.checkIfTestScoreExists(id));
    }
}
