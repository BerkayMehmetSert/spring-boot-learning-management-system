package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Enrollment @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val date: LocalDate,
    val cancelled: Boolean,
    val cancellationReason: String?,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "student_id")
    val student: Student?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_per_cycle_id")
    val coursePerCycle: CoursePerCycle?,

    @OneToMany(mappedBy = "enrollment")
    val tests: List<Test>? = emptyList()
) : BaseEntity {

    constructor(
        date: LocalDate,
        cancelled: Boolean,
        createdDate: LocalDate,
        updatedDate: LocalDate?,
        student: Student?,
        coursePerCycle: CoursePerCycle?
    ) :
            this("", date, cancelled, null, createdDate, updatedDate, student, coursePerCycle)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Enrollment

        if (id != other.id) return false
        if (date != other.date) return false
        if (cancelled != other.cancelled) return false
        if (cancellationReason != other.cancellationReason) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + date.hashCode()
        result = 31 * result + cancelled.hashCode()
        result = 31 * result + (cancellationReason?.hashCode() ?: 0)
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Enrollment(id=$id, date=$date, cancelled=$cancelled, cancellationReason=$cancellationReason," +
                " createdDate=$createdDate, updatedDate=$updatedDate, " +
                "student=$student, coursePerCycle=$coursePerCycle, tests=$tests)"
    }
}