package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Category @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val categoryNo: String,
    val description: String,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @OneToMany(mappedBy = "category")
    val courses: List<Course>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (categoryNo != other.categoryNo) return false
        if (description != other.description) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + categoryNo.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + (updatedDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Category(id=$id, categoryNo='$categoryNo', description='$description', createdDate=$createdDate," +
                " updatedDate=$updatedDate, courses=$courses)"
    }
}
