package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class Student @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val studentNo: String,
    val name: String,
    val email: String,
    val birthDate: String,
    val phoneNo: String,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @OneToMany(mappedBy = "student")
    val testScores: List<TestScore>? = emptyList(),

    @OneToMany(mappedBy = "student")
    val enrollments: List<Enrollment>? = emptyList(),

    @OneToMany(mappedBy = "student")
    val attendances: List<Attendance>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false
        if (name != other.studentNo) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (birthDate != other.birthDate) return false
        if (phoneNo != other.phoneNo) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + studentNo.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + phoneNo.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Student(id=$id, studentNo='$studentNo', name='$name', email='$email', birthDate='$birthDate'," +
                " phoneNo='$phoneNo', createdDate=$createdDate, updatedDate=$updatedDate, " +
                "testScores=$testScores, enrollments=$enrollments, attendances=$attendances)"
    }
}