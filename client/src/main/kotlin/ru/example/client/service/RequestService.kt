package ru.example.client.service

import mu.KLogging
import org.springframework.stereotype.Service
import ru.example.client.api.ServerApi
import ru.example.client.configuration.Params
import java.util.concurrent.Executors
import kotlin.random.Random

@Service
class RequestService(
    private val serverApi: ServerApi,
    private val params: Params
) {
    fun start() {
        val ids = params.getRange()

        if (params.getCounterSum() == 0) {
            return
        }

        val executorService = Executors.newFixedThreadPool(params.getCounterSum())

        for (i in 0 until params.rCount) {
            executorService.execute(Reader("reader - $i" , serverApi, ids))
        }

        for (i in 0 until params.wCount) {
            executorService.execute(Writer("writer - $i",serverApi, ids))
        }
    }
}

class Reader(
    private val name: String,
    private val serverApi: ServerApi,
    private val ids: LongRange,
) : Runnable {
    override fun run() {
        var flag = true
        var id: Long
        while (flag) {
            try {
                id = ids.random()
                logger.info { "$name send request with id $id" }
                serverApi.get(id)
                    .also { logger.info { "response $it" } }
                Thread.sleep(Random.nextLong(1_000, 3_000))
            } catch (e: Exception) {
                logger.info { e }
                flag = false
            }
        }
    }

    companion object : KLogging()
}

class Writer(
    private val name: String,
    private val serverApi: ServerApi,
    private val ids: LongRange,
) : Runnable {
    override fun run() {
        var flag = true
        var id: Long
        while (flag) {
            try {
                id = ids.random()
                val amount = Random.nextLong(-500, 500)
                logger.info { "$name send request with id $id amount $amount" }
                serverApi.add(id, amount)
                Thread.sleep(Random.nextLong(1_000, 3_000))
            } catch (e: Exception) {
                logger.info { e }
                flag = false
            }
        }
    }

    companion object : KLogging()
}