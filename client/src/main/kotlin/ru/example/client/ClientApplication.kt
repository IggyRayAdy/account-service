package ru.example.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import ru.example.client.service.RequestService

@SpringBootApplication
@EnableFeignClients
class ClientApplication

fun main(args: Array<String>) {
    val context = runApplication<ClientApplication>(*args)

    context.getBean(RequestService::class.java)
        .start()
}