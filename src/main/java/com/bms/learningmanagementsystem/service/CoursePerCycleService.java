package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.CoursePerCycleDto;
import com.bms.learningmanagementsystem.dto.converter.CoursePerCycleDtoConverter;
import com.bms.learningmanagementsystem.model.CoursePerCycle;
import com.bms.learningmanagementsystem.repositoy.CoursePerCycleRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateCoursePerCycleRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCoursePerCycleRequest;
import com.bms.learningmanagementsystem.service.rules.CoursePerCycleBusinessRules;
import com.bms.learningmanagementsystem.service.validations.CoursePerCycleValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePerCycleService {
    private final CoursePerCycleRepository coursePerCycleRepository;
    private final CourseService courseService;
    private final CycleService cycleService;
    private final CoursePerCycleDtoConverter converter;
    private final CoursePerCycleBusinessRules rules;
    private final CoursePerCycleValidator validator;

    public CoursePerCycleService(CoursePerCycleRepository coursePerCycleRepository,
                                 CourseService courseService,
                                 CycleService cycleService,
                                 CoursePerCycleDtoConverter converter,
                                 CoursePerCycleBusinessRules rules,
                                 CoursePerCycleValidator validator) {
        this.coursePerCycleRepository = coursePerCycleRepository;
        this.courseService = courseService;
        this.cycleService = cycleService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createCoursePerCycle(final CreateCoursePerCycleRequest request) {
        BusinessRules.run(() -> rules.checkIfCoursePerCycleEndsBeforeItStarts(request.startDate(), request.endDate()));

        ValidationRules.run(() -> {
            validator.notEmpty(request.startDate().toString(), ValidationMessages.COURSE_PER_CYCLE_START_DATE_REQUIRED);
            validator.notEmpty(request.endDate().toString(), ValidationMessages.COURSE_PER_CYCLE_END_DATE_REQUIRED);
            validator.notEmpty(request.courseId(), ValidationMessages.COURSE_PER_CYCLE_COURSE_ID_REQUIRED);
            validator.notEmpty(request.cycleId(), ValidationMessages.COURSE_PER_CYCLE_CYCLE_ID_REQUIRED);
        });

        var coursePerCycle = new CoursePerCycle(
                request.startDate(),
                request.endDate(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                courseService.getCourse(request.courseId()),
                cycleService.getCycle(request.cycleId())
        );

        coursePerCycleRepository.save(coursePerCycle);
    }

    public void updateCoursePerCycle(final String id, final UpdateCoursePerCycleRequest request) {
        BusinessRules.run(() -> rules.checkIfCoursePerCycleEndsBeforeItStarts(request.startDate(), request.endDate()));

        ValidationRules.run(() -> {
            validator.notEmpty(request.startDate().toString(), ValidationMessages.COURSE_PER_CYCLE_START_DATE_REQUIRED);
            validator.notEmpty(request.endDate().toString(), ValidationMessages.COURSE_PER_CYCLE_END_DATE_REQUIRED);
            validator.notEmpty(request.courseId(), ValidationMessages.COURSE_PER_CYCLE_COURSE_ID_REQUIRED);
            validator.notEmpty(request.cycleId(), ValidationMessages.COURSE_PER_CYCLE_CYCLE_ID_REQUIRED);
        });

        var coursePerCycle = getCoursePerCycle(id);

        var updatedCoursePerCycle = new CoursePerCycle(
                coursePerCycle.getId(),
                request.startDate(),
                request.endDate(),
                coursePerCycle.getCreatedDate(),
                DateHelper.getCurrentDate(),
                courseService.getCourse(request.courseId()),
                cycleService.getCycle(request.cycleId()),
                coursePerCycle.getTeacherPerCourses(),
                coursePerCycle.getEnrollments(),
                coursePerCycle.getClassrooms()
        );

        coursePerCycleRepository.save(updatedCoursePerCycle);
    }

    public void deleteCoursePerCycle(final String id) {
        coursePerCycleRepository.delete(getCoursePerCycle(id));
    }

    public CoursePerCycleDto getCoursePerCycleById(final String id) {
        return converter.convert(getCoursePerCycle(id));
    }

    public List<CoursePerCycleDto> getAllCoursePerCycles() {
        var coursePerCycles = coursePerCycleRepository.findAll();

        BusinessRules.run(() -> rules.checkIfCoursePerCycleListIsEmpty(coursePerCycles));

        return converter.convert(coursePerCycles);
    }

    protected CoursePerCycle getCoursePerCycle(final String id) {
        var coursePerCycle = BusinessRules.run(() -> rules.checkIfCoursePerCycleExists(id));

        return coursePerCycle;
    }
}
