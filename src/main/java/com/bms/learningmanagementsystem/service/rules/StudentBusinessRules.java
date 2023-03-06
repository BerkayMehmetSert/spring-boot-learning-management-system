package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Student;
import com.bms.learningmanagementsystem.repositoy.StudentRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentBusinessRules {
    private final StudentRepository studentRepository;

    public StudentBusinessRules(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void checkIfStudentExists(String name){
        if(studentRepository.existsByNameIgnoreCase(name)){
            throw new BusinessException(BusinessMessages.STUDENT_ALREADY_EXISTS);
        }
    }

    public void checkIfStudentExistByEmail(String email){
        if(studentRepository.existsByEmailIgnoreCase(email)){
            throw new BusinessException(BusinessMessages.STUDENT_ALREADY_EXISTS);
        }
    }

    public Student checkIfStudentExistsById(String id){

        return studentRepository.findById(id)
                .orElseThrow(()->new NotFoundException(BusinessMessages.STUDENT_NOT_FOUND));
    }

    public Student checkIfStudentExistsByStudentNo(String studentNo){

        return studentRepository.findByStudentNo(studentNo)
                .orElseThrow(()->new NotFoundException(BusinessMessages.STUDENT_NOT_FOUND));
    }

    public void checkIfStudentListIsEmpty(List<Student> students){
        if(students.isEmpty()){
            throw new NotFoundException(BusinessMessages.STUDENT_LIST_IS_EMPTY);
        }
    }
}
