package com.example.feign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableReactiveFeignClients
@SpringBootApplication
class FeignApplication

fun main(args: Array<String>) {
    runApplication<FeignApplication>(*args)
}
