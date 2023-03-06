package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.StudentDto;
import com.bms.learningmanagementsystem.dto.converter.StudentDtoConverter;
import com.bms.learningmanagementsystem.model.Student;
import com.bms.learningmanagementsystem.repositoy.StudentRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateStudentRequest;
import com.bms.learningmanagementsystem.service.request.UpdateStudentRequest;
import com.bms.learningmanagementsystem.service.rules.StudentBusinessRules;
import com.bms.learningmanagementsystem.service.validations.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentDtoConverter converter;
    private final StudentBusinessRules rules;
    private final StudentValidator validator;

    public StudentService(StudentRepository studentRepository, StudentDtoConverter converter,
                          StudentBusinessRules rules, StudentValidator validator) {
        this.studentRepository = studentRepository;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createStudent(final CreateStudentRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfStudentExists(request.name());
            rules.checkIfStudentExistByEmail(request.email());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.name(), ValidationMessages.STUDENT_NAME_REQUIRED);
            validator.notEmpty(request.email(), ValidationMessages.STUDENT_EMAIL_REQUIRED);
            validator.notEmpty(request.birthDate(), ValidationMessages.STUDENT_BIRTH_DATE_REQUIRED);
            validator.notEmpty(request.phoneNo(), ValidationMessages.STUDENT_PHONE_NO_REQUIRED);
            validator.minCharacters(request.name(), 3, ValidationMessages.STUDENT_NAME_MIN_CHARACTERS);
            validator.email(request.email(), ValidationMessages.EMAIL_REGEX, ValidationMessages.STUDENT_EMAIL_INVALID);
        });

        var student = new Student(
                NumberGenerator.generateRandomString(10),
                request.name(),
                request.email(),
                request.birthDate(),
                request.phoneNo(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate()
        );

        studentRepository.save(student);
    }

    public void updateStudent(final String id, final UpdateStudentRequest request) {
        ValidationRules.run(() -> {
            validator.notEmpty(request.name(), ValidationMessages.STUDENT_NAME_REQUIRED);
            validator.notEmpty(request.email(), ValidationMessages.STUDENT_EMAIL_REQUIRED);
            validator.notEmpty(request.birthDate(), ValidationMessages.STUDENT_BIRTH_DATE_REQUIRED);
            validator.notEmpty(request.phoneNo(), ValidationMessages.STUDENT_PHONE_NO_REQUIRED);
            validator.minCharacters(request.name(), 3, ValidationMessages.STUDENT_NAME_MIN_CHARACTERS);
            validator.email(request.email(), ValidationMessages.EMAIL_REGEX, ValidationMessages.STUDENT_EMAIL_INVALID);
        });

        var student = getStudent(id);

        var updatedStudent = new Student(
                student.getId(),
                student.getStudentNo(),
                request.name(),
                request.email(),
                request.birthDate(),
                request.phoneNo(),
                student.getCreatedDate(),
                DateHelper.getCurrentDate(),
                student.getTestScores(),
                student.getEnrollments(),
                student.getAttendances()
        );

        studentRepository.save(updatedStudent);
    }

    public void deleteStudent(final String id) {
        studentRepository.delete(getStudent(id));
    }

    public StudentDto getStudentById(final String id) {
        return converter.convert(getStudent(id));
    }

    public StudentDto getStudentByStudentNo(final String studentNo) {
        return converter.convert(BusinessRules.run(() -> rules.checkIfStudentExistsByStudentNo(studentNo)));
    }

    public List<StudentDto> getAllStudents() {
        var students = studentRepository.findAll();

        BusinessRules.run(() -> rules.checkIfStudentListIsEmpty(students));

        return converter.convert(students);
    }

    protected Student getStudent(final String id) {
        return BusinessRules.run(() -> rules.checkIfStudentExistsById(id));
    }
}
