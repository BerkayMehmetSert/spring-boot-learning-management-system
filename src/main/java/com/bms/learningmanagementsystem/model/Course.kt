package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Course @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val courseNo: String,
    val description: String,
    @Column(columnDefinition = "TEXT")
    val abstractDescription: String,
    @Column(columnDefinition = "TEXT")
    val bibliography: String,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category_id")
    val category: Category?,

    @OneToMany(mappedBy = "course")
    val coursePerCycles: List<CoursePerCycle>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Course

        if (id != other.id) return false
        if (courseNo != other.courseNo) return false
        if (description != other.description) return false
        if (abstractDescription != other.abstractDescription) return false
        if (bibliography != other.bibliography) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + courseNo.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + abstractDescription.hashCode()
        result = 31 * result + bibliography.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + (updatedDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Course(id=$id, courseNo='$courseNo', description='$description', " +
                "abstractDescription='$abstractDescription', bibliography='$bibliography', createdDate=$createdDate, " +
                "updatedDate=$updatedDate, category=$category," +
                " coursePerCycles=$coursePerCycles)"
    }
}

