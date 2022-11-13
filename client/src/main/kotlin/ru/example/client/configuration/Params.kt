package ru.example.client.configuration

import mu.KLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@ConfigurationProperties("params")
class Params {
    @PostConstruct
    private fun init() {
        logger.info { "Parameters are initialized: $this" }
    }

    var idsRangeFirst: Long = 0

    var idsRangeEnd: Long = 0

    var rCount: Int = 0

    var wCount: Int = 0

    fun getRange() = LongRange(idsRangeFirst, idsRangeEnd)

    fun getCounterSum() = rCount + wCount

    override fun toString(): String {
        return "idsRangeFirst=$idsRangeFirst, idsRangeEnd=$idsRangeEnd, rCount=$rCount, wCount=$wCount"
    }

    companion object : KLogging()
}