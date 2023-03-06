package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.AttendanceDto;
import com.bms.learningmanagementsystem.dto.converter.AttendanceDtoConverter;
import com.bms.learningmanagementsystem.model.Attendance;
import com.bms.learningmanagementsystem.repositoy.AttendanceRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.request.CreateAttendanceRequest;
import com.bms.learningmanagementsystem.service.request.UpdateAttendanceRequest;
import com.bms.learningmanagementsystem.service.rules.AttendanceBusinessRules;
import com.bms.learningmanagementsystem.service.validations.AttendanceValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final ClassroomService classroomService;
    private final StudentService studentService;
    private final AttendanceDtoConverter converter;
    private final AttendanceBusinessRules rules;
    private final AttendanceValidator validator;

    public AttendanceService(AttendanceRepository attendanceRepository, ClassroomService classroomService,
                             StudentService studentService, AttendanceDtoConverter converter,
                             AttendanceBusinessRules rules, AttendanceValidator validator) {
        this.attendanceRepository = attendanceRepository;
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createAttendance(final CreateAttendanceRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfAttendanceTimeArrivalIsBeforeTimeLeave(request.timeArrival(), request.timeLeave());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.timeArrival().toString(), ValidationMessages.ATTENDANCE_TIME_ARRIVAL_IS_REQUIRED);
            validator.notEmpty(request.timeLeave().toString(), ValidationMessages.ATTENDANCE_TIME_LEAVE_IS_REQUIRED);
            validator.notEmpty(request.classroomId(), ValidationMessages.ATTENDANCE_CLASSROOM_ID_IS_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.ATTENDANCE_STUDENT_ID_IS_REQUIRED);
        });

        var attendance = new Attendance(
                request.timeArrival(),
                request.timeLeave(),
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate(),
                classroomService.getClassroom(request.classroomId()),
                studentService.getStudent(request.studentId())
        );

        attendanceRepository.save(attendance);
    }

    public void updateAttendance(final String id, final UpdateAttendanceRequest request) {
        BusinessRules.run(() -> {
            rules.checkIfAttendanceTimeArrivalIsBeforeTimeLeave(request.timeArrival(), request.timeLeave());
        });

        ValidationRules.run(() -> {
            validator.notEmpty(request.timeArrival().toString(), ValidationMessages.ATTENDANCE_TIME_ARRIVAL_IS_REQUIRED);
            validator.notEmpty(request.timeLeave().toString(), ValidationMessages.ATTENDANCE_TIME_LEAVE_IS_REQUIRED);
            validator.notEmpty(request.classroomId(), ValidationMessages.ATTENDANCE_CLASSROOM_ID_IS_REQUIRED);
            validator.notEmpty(request.studentId(), ValidationMessages.ATTENDANCE_STUDENT_ID_IS_REQUIRED);
        });

        var attendance = BusinessRules.run(() -> rules.checkIfAttendanceExists(id));

        var updatedAttendance = new Attendance(
                attendance.getId(),
                request.timeArrival(),
                request.timeLeave(),
                attendance.getCreatedDate(),
                DateHelper.getCurrentDate(),
                classroomService.getClassroom(request.classroomId()),
                studentService.getStudent(request.studentId())
        );

        attendanceRepository.save(updatedAttendance);
    }

    public void deleteAttendance(final String id) {
        var attendance = BusinessRules.run(() -> rules.checkIfAttendanceExists(id));

        attendanceRepository.delete(attendance);
    }

    public AttendanceDto getAttendanceById(final String id) {
        var attendance = BusinessRules.run(() -> rules.checkIfAttendanceExists(id));

        return converter.convert(attendance);
    }

    public List<AttendanceDto> getAllAttendances() {
        var attendanceList = attendanceRepository.findAll();

        BusinessRules.run(() -> rules.checkIfAttendanceListIsEmpty(attendanceList));

        return converter.convert(attendanceList);
    }
}
