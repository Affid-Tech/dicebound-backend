package org.affidtech.dnd.diceboundbackend.web

import org.affidtech.dnd.diceboundbackend.service.AdventureAvailableService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class AdventureAvailableController(
	private val service: AdventureAvailableService
) {
	@GetMapping("/available-adventures")
	fun getAvailable(): List<AdventureAvailableDto> = service.getAvailableAdventures()
}
