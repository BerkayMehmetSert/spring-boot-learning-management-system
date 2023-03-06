package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TestDto;
import com.bms.learningmanagementsystem.model.Test;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TestDtoConverter implements DtoConverter<TestDto, Test> {
    private final TestTestScoreDtoConverter testScoreDtoConverter;

    public TestDtoConverter(TestTestScoreDtoConverter testScoreDtoConverter) {
        this.testScoreDtoConverter = testScoreDtoConverter;
    }

    @Override
    public TestDto convert(Test from) {
        return new TestDto(
                from.getId(),
                from.getTestNo(),
                from.getTestDate(),
                from.getTestTime(),
                from.getAgenda(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getEnrollment()).getId(),
                from.getTestScores() != null ? testScoreDtoConverter.convert(from.getTestScores()) : null
        );
    }
}
