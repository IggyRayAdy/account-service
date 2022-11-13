package ru.example.service.persistence.repository

import ru.example.service.persistence.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    @Query("select a from Account a where a.id = ?1")
    fun getByIdOrNull(id: Long): Account?

//    @Query("select (count(a) > 0) from Account a where a.id = ?1")
//    override fun existsById(id: Long): Boolean
}