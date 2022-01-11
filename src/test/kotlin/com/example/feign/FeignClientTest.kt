package com.example.feign

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.Import
import reactivefeign.client.ReadTimeoutException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@Import(FeignClientConfiguration::class)
class FeignClientTest(
    @Autowired
    private val feignClient: FeignClient
) {

    lateinit var wireMockServer: WireMockServer

    @BeforeEach
    fun init() {
        wireMockServer = WireMockServer()
        if(wireMockServer.isRunning){
            wireMockServer.stop()
        }
        wireMockServer.start()
    }

    @AfterEach
    fun stop() {
        wireMockServer.stop()
    }

    @Test
    fun connectionTimeTest() {
        WireMock.stubFor(
            WireMock.post(WireMock.urlMatching("https://gorest.co.in"))
            .willReturn(WireMock.aResponse().withFixedDelay(100)))

        Assertions.assertThrows(ReadTimeoutException::class.java) {
            feignClient.test()
        }
    }
}