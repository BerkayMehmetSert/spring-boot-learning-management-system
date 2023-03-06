package com.bms.learningmanagementsystem.model

import com.bms.learningmanagementsystem.core.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate

@Entity
data class TestScore @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val score: Int,
    val createdDate: LocalDate,
    val updatedDate: LocalDate?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "test_id")
    val test: Test?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "student_id")
    val student: Student?
) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestScore

        if (id != other.id) return false
        if (score != other.score) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + score
        result = 31 * result + createdDate.hashCode()
        result = 31 * result + updatedDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "TestScore(id=$id, score=$score, createdDate=$createdDate, updatedDate=$updatedDate, " +
                "test=$test, student=$student)"
    }
}
