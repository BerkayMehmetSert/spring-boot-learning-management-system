package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.TeacherDto;
import com.bms.learningmanagementsystem.dto.converter.TeacherDtoConverter;
import com.bms.learningmanagementsystem.model.Teacher;
import com.bms.learningmanagementsystem.repositoy.TeacherRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateTeacherRequest;
import com.bms.learningmanagementsystem.service.request.UpdateTeacherRequest;
import com.bms.learningmanagementsystem.service.rules.TeacherBusinessRules;
import com.bms.learningmanagementsystem.service.validations.TeacherValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherDtoConverter converter;
    private final TeacherBusinessRules rules;
    private final TeacherValidator validator;

    public TeacherService(TeacherRepository teacherRepository, TeacherDtoConverter converter,
                          TeacherBusinessRules rules, TeacherValidator validator) {
        this.teacherRepository = teacherRepository;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createTeacher(final CreateTeacherRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfTeacherExists(request.name());
            rules.checkIfTeacherExistsByEmail(request.email());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.name(), ValidationMessages.TEACHER_NAME_REQUIRED);
            validator.notEmpty(request.email(), ValidationMessages.TEACHER_EMAIL_REQUIRED);
            validator.notEmpty(request.phoneNo(), ValidationMessages.TEACHER_PHONE_REQUIRED);
            validator.minCharacters(request.name(), 3, ValidationMessages.TEACHER_NAME_MIN_LENGTH);
            validator.email(request.email(), ValidationMessages.EMAIL_REGEX, ValidationMessages.TEACHER_EMAIL_INVALID);
        });

        var teacher = new Teacher(
                NumberGenerator.generateRandomString(10),
                request.name(),
                request.email(),
                request.phoneNo(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate()
        );

        teacherRepository.save(teacher);
    }

    public void updateTeacher(final String id, final UpdateTeacherRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.name(), ValidationMessages.TEACHER_NAME_REQUIRED);
            validator.notEmpty(request.email(), ValidationMessages.TEACHER_EMAIL_REQUIRED);
            validator.notEmpty(request.phoneNo(), ValidationMessages.TEACHER_PHONE_REQUIRED);
            validator.minCharacters(request.name(), 3, ValidationMessages.TEACHER_NAME_MIN_LENGTH);
            validator.email(request.email(), ValidationMessages.EMAIL_REGEX, ValidationMessages.TEACHER_EMAIL_INVALID);
        });

        var teacher = getTeacher(id);

        var updatedTeacher = new Teacher(
                teacher.getId(),
                teacher.getTeacherNo(),
                request.name(),
                request.email(),
                request.phoneNo(),
                teacher.getCreatedDate(),
                DateHelper.getCurrentDate(),
                teacher.getTeacherPerCourses(),
                teacher.getClassrooms()
        );

        teacherRepository.save(updatedTeacher);
    }

    public void deleteTeacher(final String id) {
        teacherRepository.delete(getTeacher(id));
    }

    public TeacherDto getTeacherById(final String id) {
        return converter.convert(getTeacher(id));
    }

    public TeacherDto getTeacherByTeacherNo(final String teacherNo) {
        return converter.convert(BusinessRules.run(() -> rules.checkIfTeacherExistsByTeacherNo(teacherNo)));
    }

    public List<TeacherDto> getAllTeachers() {
        var teachers = teacherRepository.findAll();

        BusinessRules.run(() -> rules.checkIfTeacherListIsEmpty(teachers));

        return converter.convert(teachers);
    }

    protected Teacher getTeacher(final String id) {
        return BusinessRules.run(() -> rules.checkIfTeacherExistsById(id));
    }
}
