package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Classroom @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    val classNo: String,
    val date:LocalDate,
    val startTime:LocalTime,
    val endTime: LocalTime,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "teacher_id")
    val teacher: Teacher?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_per_cycle_id")
    val coursePerCycle: CoursePerCycle?,

    @OneToMany(mappedBy = "classroom")
    val attendance: List<Attendance>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Classroom

        if (id != other.id) return false
        if (title != other.title) return false
        if (classNo != other.classNo) return false
        if (date != other.date) return false
        if (startTime != other.startTime) return false
        if (endTime != other.endTime) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + classNo.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + startTime.hashCode()
        result = 31 * result + endTime.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Classroom(id=$id, title='$title', classNo='$classNo', date=$date, startTime=$startTime, " +
                "endTime=$endTime, createdDate=$createdDate, updatedDate=$updatedDate, " +
                "teacher=$teacher, coursePerCycle=$coursePerCycle, attendance=$attendance)"
    }


}
