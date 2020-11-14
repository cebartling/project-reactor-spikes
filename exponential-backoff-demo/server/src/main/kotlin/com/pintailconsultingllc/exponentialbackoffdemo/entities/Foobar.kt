package com.pintailconsultingllc.exponentialbackoffdemo.entities

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
@Table(name = "foobars")
class Foobar(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,
        var createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toDTO(): FoobarDTO {
        return FoobarDTO(
                this.id,
                this.name,
                this.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                this.updatedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        )
    }
}

data class FoobarDTO(
        val id: Long?,
        val name: String,
        val createdAt: String?,
        val updatedAt: String?
) {
    fun toEntity(): Foobar {
        return Foobar(
                this.id,
                this.name,
                if (this.createdAt != null) LocalDateTime.parse(this.createdAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME) else LocalDateTime.now(),
                if (this.updatedAt != null) LocalDateTime.parse(this.updatedAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME) else LocalDateTime.now()
        )
    }
}