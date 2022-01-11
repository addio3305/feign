package com.example.feign

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactivefeign.FallbackFactory
import reactor.core.publisher.Mono

@Component
class FeignClientFallbackFactory: FallbackFactory<FeignClient>{
    private val log = LoggerFactory.getLogger(FeignClientFallbackFactory::class.java)

    override fun apply(error: Throwable) = object : FeignClient {

        override fun test(): Mono<SampleResponse> {
            log.error("error : ", error.toString())

            throw error
        }
    }
}