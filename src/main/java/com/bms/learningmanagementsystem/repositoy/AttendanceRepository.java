package com.bms.learningmanagementsystem.repositoy;

import com.bms.learningmanagementsystem.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
}