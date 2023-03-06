package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.CycleDto;
import com.bms.learningmanagementsystem.dto.converter.CycleDtoConverter;
import com.bms.learningmanagementsystem.model.Cycle;
import com.bms.learningmanagementsystem.repositoy.CycleRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateCycleRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCycleRequest;
import com.bms.learningmanagementsystem.service.rules.CycleBusinessRules;
import com.bms.learningmanagementsystem.service.validations.CycleValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CycleService {
    private final CycleRepository cycleRepository;
    private final CycleDtoConverter converter;
    private final CycleBusinessRules rules;
    private final CycleValidator validator;

    public CycleService(CycleRepository cycleRepository,
                        CycleDtoConverter converter,
                        CycleBusinessRules rules,
                        CycleValidator validator) {
        this.cycleRepository = cycleRepository;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createCycle(final CreateCycleRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfCycleExists(request.description());
            rules.checkIfCycleEndsBeforeStarts(request.vacationStartDate(), request.vacationEndDate());
            rules.checkIfCycleEndDateEqualsNow(request.endDate());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.description(), ValidationMessages.CYCLE_DESCRIPTION_REQUIRED);
            validator.notEmpty(request.endDate().toString(), ValidationMessages.CYCLE_END_DATE_REQUIRED);
            validator.notEmpty(request.vacationStartDate().toString(), ValidationMessages.CYCLE_VACATION_START_DATE_REQUIRED);
            validator.notEmpty(request.vacationEndDate().toString(), ValidationMessages.CYCLE_VACATION_END_DATE_REQUIRED);
        });

        var cycle = new Cycle(
                request.description(),
                DateHelper.getCurrentDate(),
                request.endDate(),
                request.vacationStartDate(),
                request.vacationEndDate(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate()
        );

        cycleRepository.save(cycle);
    }

    public void updateCycle(final String id, final UpdateCycleRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfCycleEndsBeforeStarts(request.vacationStartDate(), request.vacationEndDate());
            rules.checkIfCycleEndDateEqualsNow(request.endDate());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.description(), ValidationMessages.CYCLE_DESCRIPTION_REQUIRED);
            validator.notEmpty(request.endDate().toString(), ValidationMessages.CYCLE_END_DATE_REQUIRED);
            validator.notEmpty(request.vacationStartDate().toString(), ValidationMessages.CYCLE_VACATION_START_DATE_REQUIRED);
            validator.notEmpty(request.vacationEndDate().toString(), ValidationMessages.CYCLE_VACATION_END_DATE_REQUIRED);
        });
        var cycle = getCycle(id);

        var updatedCycle = new Cycle(
                cycle.getId(),
                request.description(),
                request.startDate(),
                request.endDate(),
                request.vacationStartDate(),
                request.vacationEndDate(),
                cycle.getCreatedDate(),
                DateHelper.getCurrentDate(),
                cycle.getCoursePerCycles()
        );

        cycleRepository.save(updatedCycle);
    }

    public void deleteCycle(final String id) {
        cycleRepository.delete(getCycle(id));
    }

    public CycleDto getCycleById(final String id) {
        return converter.convert(getCycle(id));
    }

    public List<CycleDto> getAllCycles() {
        var cycles = cycleRepository.findAll();

        BusinessRules.run(() -> rules.checkIfCycleListIsEmpty(cycles));

        return converter.convert(cycles);
    }

    protected Cycle getCycle(String id) {
        return BusinessRules.run(() -> rules.checkIfCycleExistsById(id));
    }
}
