package ru.example.client.utils

import com.fasterxml.jackson.databind.ObjectMapper
import feign.jackson.JacksonEncoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import ru.example.client.configuration.CustomObjectMapper

class Encoder(
    mapper: ObjectMapper = CustomObjectMapper()
) : JacksonEncoder()