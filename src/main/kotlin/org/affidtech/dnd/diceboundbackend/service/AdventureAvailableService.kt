package org.affidtech.dnd.diceboundbackend.service

import org.affidtech.dnd.diceboundbackend.repo.AdventureAvailableRepository
import org.affidtech.dnd.diceboundbackend.web.AdventureAvailableDto
import org.affidtech.dnd.diceboundbackend.web.DmShortDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class AdventureAvailableService(
	private val adventureRepo: AdventureAvailableRepository,
	@Value("\${telegram.signup-username}") private val signupUsername: String
) {
	fun getAvailableAdventures(): List<AdventureAvailableDto> {
		return adventureRepo.findAllAvailableAdventures().map { a ->
			AdventureAvailableDto(
				adventureId = a.getAdventureId(),
				title = a.getTitle(),
				coverUrl = a.getCoverUrl(),
				type = a.getType(),
				gameSystem = a.getGameSystem(),
				dm = DmShortDto(a.getDmName(), a.getDmBio()),
				description = a.getDescription(),
				priceTokens = a.getPriceUnits(),
				freeSeats = a.getFreeSeats(),
				minPlayers = a.getMinPlayers(),
				maxPlayers = a.getMaxPlayers(),
				signupLink = buildSignupLink(a.getTitle())
			)
		}
	}
	
	private fun buildSignupLink(title: String): String {
		val message = "Привет! Хочу записаться на игру «$title»."
		val encoded = URLEncoder.encode(message, StandardCharsets.UTF_8)
		return "https://t.me/$signupUsername?text=$encoded"
	}
}
