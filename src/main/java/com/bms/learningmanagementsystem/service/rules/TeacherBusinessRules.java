package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Teacher;
import com.bms.learningmanagementsystem.repositoy.TeacherRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherBusinessRules {
    private final TeacherRepository teacherRepository;

    public TeacherBusinessRules(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void checkIfTeacherExists(String name){
        if(teacherRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(BusinessMessages.TEACHER_ALREADY_EXISTS);
        }
    }

    public void checkIfTeacherExistsByEmail(String email){
        if(teacherRepository.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.TEACHER_ALREADY_EXISTS);
        }
    }

    public Teacher checkIfTeacherExistsById(String  id){
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.TEACHER_NOT_FOUND));
    }

    public Teacher checkIfTeacherExistsByTeacherNo(String teacherNo){
        return teacherRepository.findByTeacherNo(teacherNo)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.TEACHER_NOT_FOUND));
    }

    public void checkIfTeacherListIsEmpty(List<Teacher> teachers){
        if(teachers.isEmpty()){
            throw new NotFoundException(BusinessMessages.TEACHER_LIST_IS_EMPTY);
        }
    }
}
