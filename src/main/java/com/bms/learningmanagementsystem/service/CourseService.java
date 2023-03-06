package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.CourseDto;
import com.bms.learningmanagementsystem.dto.converter.CourseDtoConverter;
import com.bms.learningmanagementsystem.model.Course;
import com.bms.learningmanagementsystem.repositoy.CourseRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateCourseRequest;
import com.bms.learningmanagementsystem.service.request.UpdateCourseRequest;
import com.bms.learningmanagementsystem.service.rules.CourseBusinessRules;
import com.bms.learningmanagementsystem.service.validations.CourseValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseDtoConverter converter;
    private final CourseBusinessRules rules;
    private final CourseValidator validator;

    public CourseService(CourseRepository courseRepository,
                         CategoryService categoryService,
                         CourseDtoConverter converter,
                         CourseBusinessRules rules,
                         CourseValidator validator) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createCourse(final CreateCourseRequest request) {
        BusinessRules.run(() -> rules.checkIfCourseExists(request.description()));
        ValidationRules.run(() -> {
            validator.notEmpty(request.description(),
                    ValidationMessages.COURSE_DESCRIPTION_REQUIRED
            );
            validator.minCharacters(request.description(),
                    3,
                    ValidationMessages.COURSE_DESCRIPTION_MIN_LENGTH
            );
            validator.notEmpty(request.abstractDescription(),
                    ValidationMessages.COURSE_ABSTRACT_DESCRIPTION_REQUIRED
            );
            validator.minCharacters(request.abstractDescription(),
                    3,
                    ValidationMessages.COURSE_ABSTRACT_DESCRIPTION_MIN_LENGTH
            );
            validator.notEmpty(request.bibliography(),
                    ValidationMessages.COURSE_BIBLIOGRAPHY_REQUIRED
            );
            validator.minCharacters(request.bibliography(),
                    3,
                    ValidationMessages.COURSE_BIBLIOGRAPHY_MIN_LENGTH
            );
            validator.notEmpty(request.categoryId(),
                    ValidationMessages.COURSE_CATEGORY_ID_REQUIRED
            );
        });

        var course = new Course(
                NumberGenerator.generateRandomString(10),
                request.description(),
                request.abstractDescription(),
                request.bibliography(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                categoryService.getCategory(request.categoryId())
        );

        courseRepository.save(course);
    }

    public void updateCourse(final String id, final UpdateCourseRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.description(),
                    ValidationMessages.COURSE_DESCRIPTION_REQUIRED
            );
            validator.minCharacters(request.description(),
                    3,
                    ValidationMessages.COURSE_DESCRIPTION_MIN_LENGTH
            );
            validator.notEmpty(request.abstractDescription(),
                    ValidationMessages.COURSE_ABSTRACT_DESCRIPTION_REQUIRED
            );
            validator.minCharacters(request.abstractDescription(),
                    3,
                    ValidationMessages.COURSE_ABSTRACT_DESCRIPTION_MIN_LENGTH
            );
            validator.notEmpty(request.bibliography(),
                    ValidationMessages.COURSE_BIBLIOGRAPHY_REQUIRED
            );
            validator.minCharacters(request.bibliography(),
                    3,
                    ValidationMessages.COURSE_BIBLIOGRAPHY_MIN_LENGTH
            );
            validator.notEmpty(request.categoryId(),
                    ValidationMessages.COURSE_CATEGORY_ID_REQUIRED
            );
        });

        var course = getCourse(id);

        var updatedCourse = new Course(
                course.getId(),
                course.getCourseNo(),
                request.description(),
                request.abstractDescription(),
                request.bibliography(),
                course.getCreatedDate(),
                DateHelper.getCurrentDate(),
                categoryService.getCategory(request.categoryId()),
                course.getCoursePerCycles()
        );

        courseRepository.save(updatedCourse);
    }

    public void deleteCourse(final String id) {
        courseRepository.delete(getCourse(id));
    }

    public CourseDto getCourseById(final String id) {
        return converter.convert(getCourse(id));
    }

    public CourseDto getCourseByCourseNo(final String courseNo) {
        var course = BusinessRules.run(() -> rules.checkIfCourseExistsByCourseNo(courseNo));

        return converter.convert(course);
    }

    public List<CourseDto> getAllCourses() {
        var courses = courseRepository.findAll();

        BusinessRules.run(() -> rules.checkIfCourseListIsEmpty(courses));

        return converter.convert(courses);
    }

    protected Course getCourse(final String id) {
        return BusinessRules.run(() -> rules.checkIfCourseExistsById(id));
    }
}
