package com.pintailconsultingllc.exponentialbackoffdemo.controllers

import com.pintailconsultingllc.exponentialbackoffdemo.entities.Foobar
import com.pintailconsultingllc.exponentialbackoffdemo.entities.FoobarDTO
import com.pintailconsultingllc.exponentialbackoffdemo.services.FoobarService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.net.URI


@RestController
@RequestMapping("/api/v1/foobars", produces = [MediaType.APPLICATION_JSON_VALUE])
class FoobarController(
        private val foobarService: FoobarService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Mono<FoobarDTO?>? {
        return Mono.fromCallable {
            val optional = foobarService.getById(id)
            if (optional.isPresent)     {
                optional.get().toDTO()
            } else {
                null
            }
        }.subscribeOn(Schedulers.elastic())
    }

    @GetMapping
    fun getPage(pageable: Pageable): Mono<Page<FoobarDTO?>> {
        return Mono.fromCallable { foobarService.retrieve(pageable).map { foobar: Foobar? -> foobar?.toDTO() } }
                .subscribeOn(Schedulers.elastic())
    }

    @PostMapping
    fun create(@RequestBody dto: FoobarDTO): Mono<ResponseEntity<Void>> {
        return Mono.fromCallable {
            val saved = this.foobarService.save(dto.toEntity())
            val locationUri = URI.create("/api/v1/foobars/${saved.id}")
            ResponseEntity.created(locationUri).build<Void>()
        }.subscribeOn(Schedulers.elastic())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody dto: FoobarDTO): Mono<ResponseEntity<Void>> {
        return Mono.fromCallable {
            this.foobarService.save(dto.toEntity())
            ResponseEntity.noContent().build<Void>()
        }.subscribeOn(Schedulers.elastic())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Mono<ResponseEntity<Void>> {
        return Mono.fromCallable {
            val optional = this.foobarService.getById(id)
            if (optional.isPresent) {
                this.foobarService.delete(optional.get())
                ResponseEntity.noContent().build<Void>()
            } else {
                ResponseEntity.notFound().build<Void>()
            }
        }.subscribeOn(Schedulers.elastic())
    }
}

