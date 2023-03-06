package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.ClassroomDto;
import com.bms.learningmanagementsystem.dto.converter.ClassroomDtoConverter;
import com.bms.learningmanagementsystem.model.Classroom;
import com.bms.learningmanagementsystem.repositoy.ClassroomRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateClassroomRequest;
import com.bms.learningmanagementsystem.service.request.UpdateClassroomRequest;
import com.bms.learningmanagementsystem.service.rules.ClassroomBusinessRules;
import com.bms.learningmanagementsystem.service.validations.ClassroomValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final TeacherService teacherService;
    private final CoursePerCycleService coursePerCycleService;
    private final ClassroomDtoConverter converter;
    private final ClassroomBusinessRules rules;
    private final ClassroomValidator validator;

    public ClassroomService(ClassroomRepository classroomRepository, TeacherService teacherService,
                            CoursePerCycleService coursePerCycleService, ClassroomDtoConverter converter,
                            ClassroomBusinessRules rules, ClassroomValidator validator) {
        this.classroomRepository = classroomRepository;
        this.teacherService = teacherService;
        this.coursePerCycleService = coursePerCycleService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createClassroom(final CreateClassroomRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfClassroomExistsByTitle(request.title());
            rules.checkIfClassroomDateIsBeforeToday(request.date());
            rules.checkIfClassroomEndTimeIsBeforeStartTime(request.startTime(), request.endTime());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.title(), ValidationMessages.CLASSROOM_TITLE_REQUIRED);
            validator.notEmpty(request.teacherId(), ValidationMessages.CLASSROOM_TEACHER_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.CLASSROOM_COURSE_PER_CYCLE_ID_REQUIRED);
            validator.notEmpty(request.date().toString(), ValidationMessages.CLASSROOM_DATE_REQUIRED);
            validator.notEmpty(request.startTime().toString(), ValidationMessages.CLASSROOM_START_TIME_REQUIRED);
            validator.notEmpty(request.endTime().toString(), ValidationMessages.CLASSROOM_END_TIME_REQUIRED);
        });

        var classroom = new Classroom(
                request.title(),
                NumberGenerator.generateRandomString(5),
                request.date(),
                request.startTime(),
                request.endTime(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                teacherService.getTeacher(request.teacherId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId())
        );

        classroomRepository.save(classroom);
    }

    public void updateClassroom(final String id, final UpdateClassroomRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfClassroomDateIsBeforeToday(request.date());
            rules.checkIfClassroomEndTimeIsBeforeStartTime(request.startTime(), request.endTime());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.title(), ValidationMessages.CLASSROOM_TITLE_REQUIRED);
            validator.notEmpty(request.teacherId(), ValidationMessages.CLASSROOM_TEACHER_ID_REQUIRED);
            validator.notEmpty(request.coursePerCycleId(), ValidationMessages.CLASSROOM_COURSE_PER_CYCLE_ID_REQUIRED);
            validator.notEmpty(request.date().toString(), ValidationMessages.CLASSROOM_DATE_REQUIRED);
            validator.notEmpty(request.startTime().toString(), ValidationMessages.CLASSROOM_START_TIME_REQUIRED);
            validator.notEmpty(request.endTime().toString(), ValidationMessages.CLASSROOM_END_TIME_REQUIRED);
        });

        var classroom = getClassroom(id);

        var updatedClassroom = new Classroom(
                classroom.getId(),
                request.title(),
                classroom.getClassNo(),
                request.date(),
                request.startTime(),
                request.endTime(),
                classroom.getCreatedDate(),
                DateHelper.getCurrentDate(),
                teacherService.getTeacher(request.teacherId()),
                coursePerCycleService.getCoursePerCycle(request.coursePerCycleId()),
                classroom.getAttendance()
        );

        classroomRepository.save(updatedClassroom);
    }

    public void deleteClassroom(final String id) {
        classroomRepository.delete(getClassroom(id));
    }

    public ClassroomDto getClassroomById(final String id) {
        return converter.convert(getClassroom(id));
    }

    public List<ClassroomDto> getAllClassrooms() {
        var classrooms = classroomRepository.findAll();

        BusinessRules.run(() -> rules.checkIfClassroomListIsEmpty(classrooms));

        return converter.convert(classrooms);
    }

    protected Classroom getClassroom(String id) {
        return BusinessRules.run(() -> rules.checkIfClassroomExists(id));
    }
}
