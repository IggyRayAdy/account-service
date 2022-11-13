package ru.example.client.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.stereotype.Component

@Component
class CustomObjectMapper : ObjectMapper() {
    init { configure(this) }

    companion object {
        fun configure(mapper: ObjectMapper): ObjectMapper {
            return mapper.apply {
                registerKotlinModule()
            }
        }
    }
}