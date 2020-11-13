package com.pintailconsultingllc.exponentialbackoffdemo.repositories

import com.pintailconsultingllc.exponentialbackoffdemo.entities.Foobar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoobarRepository : JpaRepository<Foobar, Long> {
}