package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.TestScore;
import com.bms.learningmanagementsystem.repositoy.TestScoreRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestScoreBusinessRules {
    private final TestScoreRepository testScoreRepository;

    public TestScoreBusinessRules(TestScoreRepository testScoreRepository) {
        this.testScoreRepository = testScoreRepository;
    }

    public TestScore checkIfTestScoreExists(String id){
        return testScoreRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(BusinessMessages.TEST_SCORE_NOT_FOUND));
    }

    public void checkIfTestScoreListIsEmpty(List<TestScore> testScores){
        if(testScores.isEmpty()){
            throw new NotFoundException(BusinessMessages.TEST_SCORE_LIST_IS_EMPTY);
        }
    }
}
