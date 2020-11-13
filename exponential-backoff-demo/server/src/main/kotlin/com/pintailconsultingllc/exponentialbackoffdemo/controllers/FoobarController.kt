package com.pintailconsultingllc.exponentialbackoffdemo.controllers

import com.pintailconsultingllc.exponentialbackoffdemo.entities.Foobar
import com.pintailconsultingllc.exponentialbackoffdemo.services.FoobarService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


@RestController
@RequestMapping("/api/v1/foobars")
class FoobarController(
        private val foobarService: FoobarService
) {
    @GetMapping("/{id}")
    private fun getById(@PathVariable id: Long): Mono<Foobar?>? {
        return Mono.fromCallable { foobarService.getById(id) }.subscribeOn(Schedulers.elastic())
    }

    @GetMapping
    private fun getPage(pageable: Pageable): Mono<Page<Foobar>> {
        return Mono.fromCallable { foobarService.retrieve(pageable) }.subscribeOn(Schedulers.elastic())
    }
}