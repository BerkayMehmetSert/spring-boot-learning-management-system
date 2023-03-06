package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.TeacherPerCourseDto;
import com.bms.learningmanagementsystem.dto.converter.TeacherPerCourseDtoConverter;
import com.bms.learningmanagementsystem.model.TeacherPerCourse;
import com.bms.learningmanagementsystem.repositoy.TeacherPerCourseRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateTeacherPerCourseRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTeacherPerCourseRequest;
import com.bms.learningmanagementsystem.service.rules.TeacherPerCourseRules;
import com.bms.learningmanagementsystem.service.validations.TeacherPerCourseValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherPerCourseService {
    private final TeacherPerCourseRepository teacherPerCourseRepository;
    private final TeacherService teacherService;
    private final CoursePerCycleService coursePerCycleService;
    private final TeacherPerCourseDtoConverter converter;
    private final TeacherPerCourseRules rules;
    private final TeacherPerCourseValidator validator;

    public TeacherPerCourseService(TeacherPerCourseRepository teacherPerCourseRepository,
                                   TeacherService teacherService,
                                   CoursePerCycleService coursePerCycleService,
                                   TeacherPerCourseDtoConverter converter,
                                   TeacherPerCourseRules rules, TeacherPerCourseValidator validator) {
        this.teacherPerCourseRepository = teacherPerCourseRepository;
        this.teacherService = teacherService;
        this.coursePerCycleService = coursePerCycleService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createTeacherPerCourse(final CreateTeacherPerCourseRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.teacherId(), ValidationMessages.TEACHER_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.COURSE_PER_CYCLE_ID_REQUIRED);
        });

        var TeacherPerCourse = new TeacherPerCourse(
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                teacherService.getTeacher(request.teacherId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId())
        );

        teacherPerCourseRepository.save(TeacherPerCourse);
    }

    public void updateTeacherPerCourse(final String id, final UpdateTeacherPerCourseRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.teacherId(), ValidationMessages.TEACHER_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.COURSE_PER_CYCLE_ID_REQUIRED);
        });

        var teacherPerCourse = getTeacherPerCourse(id);

        var updatedTeacherPerCourse = new TeacherPerCourse(
                teacherPerCourse.getId(),
                teacherPerCourse.getCreatedDate(),
                DateHelper.getCurrentDate(),
                teacherService.getTeacher(request.teacherId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId())
        );

        teacherPerCourseRepository.save(updatedTeacherPerCourse);
    }

    public void deleteTeacherPerCourse(final String id) {
        teacherPerCourseRepository.delete(getTeacherPerCourse(id));
    }

    public TeacherPerCourseDto getTeacherPerCourseById(final String id) {
        return converter.convert(BusinessRules.run(() -> rules.checkIfTeacherPerCourseExists(id)));
    }

    public List<TeacherPerCourseDto> getAllTeacherPerCourses() {
        var teacherPerCourses = teacherPerCourseRepository.findAll();

        BusinessRules.run(() -> rules.checkIfTeacherPerCourseListIsEmpty(teacherPerCourses));

        return converter.convert(teacherPerCourses);
    }

    protected TeacherPerCourse getTeacherPerCourse(String id) {
        return BusinessRules.run(() -> rules.checkIfTeacherPerCourseExists(id));
    }
}
