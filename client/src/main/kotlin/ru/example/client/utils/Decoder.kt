package ru.example.client.utils

import com.fasterxml.jackson.databind.ObjectMapper
import feign.jackson.JacksonDecoder
import org.springframework.http.codec.json.Jackson2JsonDecoder
import ru.example.client.configuration.CustomObjectMapper

class Decoder(
    mapper: ObjectMapper = CustomObjectMapper()
) : JacksonDecoder()