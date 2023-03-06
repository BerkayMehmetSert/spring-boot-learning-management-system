package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.CycleDto;
import com.bms.learningmanagementsystem.model.Cycle;
import org.springframework.stereotype.Component;

@Component
public class CycleDtoConverter implements DtoConverter<CycleDto, Cycle> {

    @Override
    public CycleDto convert(Cycle from) {
        return new CycleDto(
                from.getId(),
                from.getDescription(),
                from.getStartDate(),
                from.getEndDate(),
                from.getVacationStartDate(),
                from.getVacationEndDate(),
                from.getCreatedDate()
        );
    }
}
