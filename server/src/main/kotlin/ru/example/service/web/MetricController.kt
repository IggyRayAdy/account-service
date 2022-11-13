package ru.example.service.web

import ru.example.service.service.MetricService
import ru.example.service.service.MetricService.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/service/metric")
class MetricController(
    private val metricService: MetricService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun addCountAmount(
        @RequestParam(name = "operation", required = true) operation: Operation
    ): Set<Map.Entry<String, Int>> = metricService.operation(operation)

}