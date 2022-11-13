package ru.example.service.service.impl

interface AccountService {
    fun getAmount(id: Int): Long

    fun addAmount(id: Int, value: Long)
}