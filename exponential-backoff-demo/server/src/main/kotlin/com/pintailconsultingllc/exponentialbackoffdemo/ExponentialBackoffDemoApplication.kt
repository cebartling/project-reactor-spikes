package com.pintailconsultingllc.exponentialbackoffdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExponentialBackoffDemoApplication

fun main(args: Array<String>) {
	runApplication<ExponentialBackoffDemoApplication>(*args)
}
