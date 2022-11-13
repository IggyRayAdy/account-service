package ru.example.service.web

import ru.example.service.service.AccountServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/service")
class AccountController(
    private val accountService: AccountServiceImpl
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun addAmount(
        @RequestParam(name = "id", required = true) id: Int,
        @RequestParam(name = "amount", required = true) value: Long
    ): Unit = accountService.addAmount(id, value)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAmount(
        @RequestParam(name = "id", required = true) id: Int
    ): Long = accountService.getAmount(id)
}