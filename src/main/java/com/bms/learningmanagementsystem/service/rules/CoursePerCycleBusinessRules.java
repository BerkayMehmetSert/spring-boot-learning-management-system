package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.CoursePerCycle;
import com.bms.learningmanagementsystem.repositoy.CoursePerCycleRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CoursePerCycleBusinessRules {
    private final CoursePerCycleRepository coursePerCycleRepository;

    public CoursePerCycleBusinessRules(CoursePerCycleRepository coursePerCycleRepository) {
        this.coursePerCycleRepository = coursePerCycleRepository;
    }

    public void checkIfCoursePerCycleEndsBeforeItStarts(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new BusinessException(BusinessMessages.COURSE_PER_CYCLE_ENDS_BEFORE_IT_STARTS);
        }
    }

    public CoursePerCycle checkIfCoursePerCycleExists(String id) {
        return coursePerCycleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.COURSE_PER_CYCLE_NOT_FOUND));
    }

    public void checkIfCoursePerCycleListIsEmpty(List<CoursePerCycle> coursePerCycleList) {
        if (coursePerCycleList.isEmpty()) {
            throw new NotFoundException(BusinessMessages.COURSE_PER_CYCLE_LIST_IS_EMPTY);
        }
    }
}
