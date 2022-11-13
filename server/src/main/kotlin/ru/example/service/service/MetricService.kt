package ru.example.service.service

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger


@Service
class MetricService {
    private val totalCountGet = AtomicInteger(0)
    private val currentCountGet = AtomicInteger(0)
    private val totalCountAdd = AtomicInteger(0)
    private val currentCountAdd = AtomicInteger(0)

    fun incGet() {
        totalCountGet.incrementAndGet()
        currentCountGet.incrementAndGet()
    }

    fun decGet() {
        currentCountGet.decrementAndGet()
    }

    fun incAdd() {
        totalCountAdd.incrementAndGet()
        currentCountAdd.incrementAndGet()
    }

    fun decAdd() {
        currentCountAdd.decrementAndGet()
    }

    fun getTotalList(): Set<Map.Entry<String, Int>> {
        return mapOf(
            Operation.TOTAL_ADD.description to totalCountAdd.get(),
            Operation.TOTAL_GET.description to totalCountGet.get()
        ).entries
    }

    fun getCurrentList(): Set<Map.Entry<String, Int>> {
        return mapOf(
            Operation.CURRENT_ADD.description to currentCountAdd.get(),
            Operation.CURRENT_GET.description to currentCountGet.get()
        ).entries
    }

    fun getTotal(): Set<Map.Entry<String, Int>> {
        return mapOf(
            Operation.TOTAL.name to totalCountAdd.get() + totalCountGet.get(),
        ).entries
    }

    fun reset(): Set<Map.Entry<String, Int>> {
        totalCountGet.set(0)
        currentCountGet.set(0)
        totalCountAdd.set(0)
        currentCountAdd.set(0)

        return mapOf(
            Operation.RESET.description to 0
        ).entries
    }

    fun operation(e: Operation): Set<Map.Entry<String, Int>> {
        return when (e) {
            Operation.TOTAL -> this.getTotal()
            Operation.TOTAL_ADD, Operation.TOTAL_GET -> this.getTotalList()
            Operation.CURRENT_ADD, Operation.CURRENT_GET -> this.getCurrentList()
            Operation.RESET -> this.reset()
        }
    }

    enum class Operation(val description: String) {
        TOTAL("Всего запросов"),
        TOTAL_ADD("Всего запросов на запись"),
        TOTAL_GET("Всего запросов на чтение"),
        CURRENT_ADD("Текущих запросов на запись"),
        CURRENT_GET("Текущих запросов на чтение"),
        RESET("Сброс счетчиков")
    }
}