package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class CoursePerCycle @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val startDate: LocalDate,
    val endDate: LocalDate,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id")
    val course: Course?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "cycle_id")
    val cycle: Cycle?,

    @OneToMany(mappedBy = "coursePerCycle")
    val teacherPerCourses: List<TeacherPerCourse>? = emptyList(),

    @OneToMany(mappedBy = "coursePerCycle")
    val enrollments: List<Enrollment>? = emptyList(),

    @OneToMany(mappedBy = "coursePerCycle")
    val classrooms: List<Classroom>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CoursePerCycle

        if (id != other.id) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "CoursePerCycle(id=$id, startDate=$startDate, endDate=$endDate, createdDate=$createdDate, " +
                "updatedDate=$updatedDate, course=$course, cycle=$cycle, " +
                "teacherPerCourses=$teacherPerCourses, enrollments=$enrollments, classrooms=$classrooms)"
    }
}