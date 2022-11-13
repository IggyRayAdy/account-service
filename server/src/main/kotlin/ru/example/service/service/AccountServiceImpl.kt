package ru.example.service.service

import ru.example.service.cashe.Cache
import ru.example.service.persistence.entity.Account
import ru.example.service.persistence.repository.AccountRepository
import ru.example.service.service.impl.AccountService
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val repository: AccountRepository,
    private val metricService: MetricService
) : AccountService {
    private val cache = Cache<Account>()

    override fun getAmount(id: Int): Long {
        metricService.incGet()

        val account = if (cache.exist(id)) {
            cache.get(id)
        } else repository.getByIdOrNull(id.toLong())
            ?.also { cache.put(id, it) }

        metricService.decGet()

        return account?.amount ?: 0
    }

    override fun addAmount(id: Int, value: Long) {
        metricService.incAdd()

        val account = if (cache.exist(id)) {
            cache.get(id)!!
        } else Account(id.toLong())

        account.apply { this.amount = value }

        repository.save(account)
        cache.put(id, account)

        metricService.decAdd()
    }

    companion object : KLogging()
}