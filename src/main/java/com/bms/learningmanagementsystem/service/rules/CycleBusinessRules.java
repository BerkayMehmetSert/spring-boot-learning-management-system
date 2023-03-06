package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Cycle;
import com.bms.learningmanagementsystem.repositoy.CycleRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CycleBusinessRules {
    private final CycleRepository cycleRepository;

    public CycleBusinessRules(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }

    public void checkIfCycleExists(String description) {
        var cycle = cycleRepository.existsByDescription(description);

        if (cycle) {
            throw new BusinessException(BusinessMessages.CYCLE_ALREADY_EXISTS);
        }
    }

    public void checkIfCycleEndsBeforeStarts(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new BusinessException(BusinessMessages.CYCLE_ENDS_BEFORE_STARTS);
        }
    }

    public void checkIfCycleEndDateEqualsNow(LocalDate endDate) {
        if (endDate != null && endDate.isEqual(LocalDate.now())) {
            throw new BusinessException(BusinessMessages.CYCLE_END_EQUALS_NOW);
        }
    }

    public Cycle checkIfCycleExistsById(String id) {
        var cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.CYCLE_NOT_FOUND));

        return cycle;
    }

    public void checkIfCycleListIsEmpty(List<Cycle> cycles) {
        if (cycles.isEmpty()) {
            throw new NotFoundException(BusinessMessages.CYCLE_LIST_EMPTY);
        }
    }
}
