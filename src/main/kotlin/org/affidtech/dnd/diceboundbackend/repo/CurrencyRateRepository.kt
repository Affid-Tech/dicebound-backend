package org.affidtech.dnd.diceboundbackend.repo

import org.affidtech.dnd.diceboundbackend.domain.CurrencyRateEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRateRepository : CrudRepository<CurrencyRateEntity, String> {
	fun findByCurrency(currency: String): CurrencyRateEntity?
}