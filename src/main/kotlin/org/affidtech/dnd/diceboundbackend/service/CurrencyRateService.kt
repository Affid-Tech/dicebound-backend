package org.affidtech.dnd.diceboundbackend.service

import org.affidtech.dnd.diceboundbackend.repo.CurrencyRateRepository
import org.affidtech.dnd.diceboundbackend.web.CurrencyRateDto
import org.springframework.stereotype.Service

@Service
class CurrencyRateService(
	private val currencyRateRepository: CurrencyRateRepository,
) {
	fun getAll(): List<CurrencyRateDto> =
		currencyRateRepository.findAll().map{cr ->
			CurrencyRateDto(cr.currency, cr.ratio)
	}
	
}
