package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TestScoreDto;
import com.bms.learningmanagementsystem.model.TestScore;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TestScoreDtoConverter implements DtoConverter<TestScoreDto, TestScore> {
    @Override
    public TestScoreDto convert(TestScore from) {
        return new TestScoreDto(
                from.getId(),
                from.getScore(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getTest()).getId(),
                Objects.requireNonNull(from.getStudent()).getId()
        );
    }
}
