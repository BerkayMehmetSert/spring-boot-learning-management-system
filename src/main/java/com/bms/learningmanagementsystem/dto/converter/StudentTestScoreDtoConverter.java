package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.StudentTestScoreDto;
import com.bms.learningmanagementsystem.model.TestScore;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentTestScoreDtoConverter implements DtoConverter<StudentTestScoreDto, TestScore> {
    @Override
    public StudentTestScoreDto convert(TestScore from) {
        return new StudentTestScoreDto(
                from.getId(),
                from.getScore(),
                from.getCreatedDate(),
                Objects.requireNonNull(from.getTest()).getId()
        );
    }
}
