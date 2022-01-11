package com.example.feign

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactivefeign.ReactiveOptions
import reactivefeign.webclient.WebReactiveOptions

@Component
class FeignClientConfiguration {

    @Bean
    fun options(): ReactiveOptions {
        return WebReactiveOptions.Builder()
            .setWriteTimeoutMillis(1)
            .setReadTimeoutMillis(15)
            .setConnectTimeoutMillis(1)
            .build()
    }
}