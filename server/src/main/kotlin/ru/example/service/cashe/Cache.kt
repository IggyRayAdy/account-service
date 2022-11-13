package ru.example.service.cashe

import mu.KLogging
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

class Cache<T> {
    private val lock: ReadWriteLock = ReentrantReadWriteLock()
//    private val cache: MutableMap<Int, Holder<T>> = ConcurrentHashMap()
    private val cache: MutableMap<Int, Holder<T>> = HashMap()

    fun put(key: Int, value: Holder<T>) {
        try {
            lock.readLock().lock()
            cache[key] = value
        } finally {
            lock.readLock().unlock()
        }
        logger.info { "add to cache new value with id $key" }
    }

    fun put(key: Int, value: T) {
        val holder = Holder(value)
        put(key, holder)
    }

    fun get(key: Int): T? {
        return cache[key]?.value
    }

    fun exist(key: Int): Boolean {
        return cache.contains(key)
    }

    companion object: KLogging()
}
