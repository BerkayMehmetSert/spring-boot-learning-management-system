package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Test @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val testNo: String,
    val testDate: LocalDate,
    val testTime: LocalTime,
    @Column(columnDefinition = "TEXT")
    val agenda: String,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "enrollment_id")
    val enrollment: Enrollment?,

    @OneToMany(mappedBy = "test")
    val testScores: List<TestScore>? = emptyList()
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Test

        if (id != other.id) return false
        if (testNo != other.testNo) return false
        if (testDate != other.testDate) return false
        if (testTime != other.testTime) return false
        if (agenda != other.agenda) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + testNo.hashCode()
        result = 31 * result + testDate.hashCode()
        result = 31 * result + testTime.hashCode()
        result = 31 * result + agenda.hashCode()
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Test(id=$id, testNo='$testNo', testDate=$testDate, testTime=$testTime, agenda='$agenda', " +
                "createdDate=$createdDate, updatedDate=$updatedDate, " +
                "enrollment=$enrollment, testScores=$testScores)"
    }
}