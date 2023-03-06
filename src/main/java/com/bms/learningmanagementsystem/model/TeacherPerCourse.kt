package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class TeacherPerCourse @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "teacher_id")
    val teacher: Teacher?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_per_cycle_id")
    val coursePerCycle: CoursePerCycle?
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TeacherPerCourse

        if (id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "TeacherPerCourse(id=$id, createdDate=$createdDate, updatedDate=$updatedDate, " +
                "teacher=$teacher, coursePerCycle=$coursePerCycle)"
    }
}
