package com.pintailconsultingllc.exponentialbackoffdemo.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "foobars")
class Foobar(
        @Id @GeneratedValue var id: Long? = null,
        var name: String,
        var createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime = LocalDateTime.now()
)