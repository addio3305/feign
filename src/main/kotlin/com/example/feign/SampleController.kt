package com.example.feign

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val feignClient: FeignClient
) {

    @GetMapping
    fun test(){
        feignClient.test()
    }
}