package ru.example.client.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("server", url = "\${feign.client.config.server.url}")
interface ServerApi {

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/v1/service"]
    )
    fun get(@RequestParam("id") id: Long): Long

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/v1/service"]
    )
    fun add(
        @RequestParam("id") id: Long,
        @RequestParam("amount") amount: Long
    ): Unit
}