package ru.example.service.persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "account")
class Account(
    @Id
    var id: Long? = null
) {
    var amount: Long = 0L
        set(value) { field += value }

    override fun toString(): String {
        return "account(id=$id, amount=$amount)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}