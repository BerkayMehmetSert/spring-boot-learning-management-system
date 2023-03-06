package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.model.Classroom;
import com.bms.learningmanagementsystem.repositoy.ClassroomRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class ClassroomBusinessRules {
    private final ClassroomRepository classroomRepository;

    public ClassroomBusinessRules(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public void checkIfClassroomExistsByTitle(String title) {
        var classroom = classroomRepository.existsByTitleIgnoreCase(title);

        if (classroom) throw new BusinessException(BusinessMessages.CLASSROOM_ALREADY_EXISTS);
    }

    public void checkIfClassroomDateIsBeforeToday(LocalDate date) {
        if (date.isBefore(DateHelper.getCurrentDate())) {
            throw new BusinessException(BusinessMessages.CLASSROOM_DATE_IS_BEFORE_TODAY);
        }
    }

    public void checkIfClassroomEndTimeIsBeforeStartTime(LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new BusinessException(BusinessMessages.CLASSROOM_END_TIME_IS_BEFORE_START_TIME);
        }
    }

    public Classroom checkIfClassroomExists(String id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.CLASSROOM_NOT_FOUND));
    }

    public void checkIfClassroomListIsEmpty(List<Classroom> classrooms) {
        if (classrooms.isEmpty()) throw new NotFoundException(BusinessMessages.CLASSROOM_LIST_IS_EMPTY);
    }
}
