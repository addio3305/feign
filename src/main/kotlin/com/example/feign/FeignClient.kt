package com.example.feign

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@Component
@ReactiveFeignClient(
    name = "feignClient",
    url = "https://gorest.co.in",
    fallbackFactory = FeignClientFallbackFactory::class,
    configuration = [FeignClientConfiguration::class])
interface FeignClient {

    @GetMapping("/public/v1/users")
    fun test(): Mono<SampleResponse>
}