package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.TeacherPerCourse;
import com.bms.learningmanagementsystem.repositoy.TeacherPerCourseRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherPerCourseRules {
    private final TeacherPerCourseRepository teacherPerCourseRepository;

    public TeacherPerCourseRules(TeacherPerCourseRepository teacherPerCourseRepository) {
        this.teacherPerCourseRepository = teacherPerCourseRepository;
    }

    public TeacherPerCourse checkIfTeacherPerCourseExists(String id){
        return teacherPerCourseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.TEACHER_PER_COURSE_NOT_FOUND));
    }

    public void checkIfTeacherPerCourseListIsEmpty(List<TeacherPerCourse> teacherPerCourses){
        if(teacherPerCourses.isEmpty()){
            throw new NotFoundException(BusinessMessages.TEACHER_PER_COURSE_LIST_IS_EMPTY);
        }
    }
}
