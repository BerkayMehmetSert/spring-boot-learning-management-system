package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.TestTestScoreDto;
import com.bms.learningmanagementsystem.model.TestScore;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TestTestScoreDtoConverter implements DtoConverter<TestTestScoreDto, TestScore> {
    @Override
    public TestTestScoreDto convert(TestScore from) {
        return new TestTestScoreDto(
                from.getId(),
                from.getScore(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getStudent()).getId()
        );
    }
}
