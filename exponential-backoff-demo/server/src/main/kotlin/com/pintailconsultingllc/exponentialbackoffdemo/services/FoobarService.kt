package com.pintailconsultingllc.exponentialbackoffdemo.services

import com.pintailconsultingllc.exponentialbackoffdemo.entities.Foobar
import com.pintailconsultingllc.exponentialbackoffdemo.repositories.FoobarRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class FoobarService(
        private val foobarRepository: FoobarRepository
) {
    fun retrieve(pageable: Pageable): Page<Foobar> {
        return foobarRepository.findAll(pageable)
    }

    fun save(foobar: Foobar): Foobar {
        return foobarRepository.save(foobar)
    }

    fun delete(foobar: Foobar) {
        return foobarRepository.delete(foobar)
    }

    fun getById(id: Long): Optional<Foobar> {
        return foobarRepository.findById(id)
    }
}