package org.affidtech.dnd.diceboundbackend.web

import org.affidtech.dnd.diceboundbackend.service.CurrencyRateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/currency-rates")
class CurrencyRateController(
	private val service: CurrencyRateService
) {
	@GetMapping
	fun getAll(): List<CurrencyRateDto> = service.getAll()
}
