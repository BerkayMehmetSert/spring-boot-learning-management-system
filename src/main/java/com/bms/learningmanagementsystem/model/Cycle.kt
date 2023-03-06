package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Cycle @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val vacationStartDate: LocalDate,
    val vacationEndDate: LocalDate,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @OneToMany(mappedBy = "cycle")
    val coursePerCycles: List<CoursePerCycle>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cycle

        if (id != other.id) return false
        if (description != other.description) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (vacationStartDate != other.vacationStartDate) return false
        if (vacationEndDate != other.vacationEndDate) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + description.hashCode()
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        result = 31 * result + vacationStartDate.hashCode()
        result = 31 * result + vacationEndDate.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Cycle(id=$id, description='$description', startDate=$startDate, endDate=$endDate, " +
                "vacationStartDate=$vacationStartDate, vacationEndDate=$vacationEndDate, createdDate=$createdDate, " +
                "updatedDate=$updatedDate, coursePerCycles=$coursePerCycles)"
    }
}
