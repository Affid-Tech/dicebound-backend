package org.affidtech.dnd.diceboundbackend.web

import java.time.OffsetDateTime
import java.util.*

data class ScheduleSessionDto(
	val sessionId: UUID,
	val adventureId: UUID,
	val adventureTitle: String,
	val type: String,
	val gameSystem: String,
	val dm: DmShortDto,
	val startTime: OffsetDateTime,
	val durationHours: Int,
	val minPlayers: Int,
	val maxPlayers: Int,
	val description: String?,
	val signupLink: String? // null если мест нет
)

data class DmShortDto(
	val name: String,
	val bio: String?
)

data class AdventureAvailableDto(
	val adventureId: UUID,
	val title: String,
	val type: String,
	val coverUrl: String,
	val gameSystem: String,
	val dm: DmShortDto,
	val description: String?,
	val priceTokens: Int?,         // цена в токенах
	val freeSeats: Int,
	val minPlayers: Int,
	val maxPlayers: Int,
	val signupLink: String?,
	val startLevel: Int
)

data class CurrencyRateDto(
	val currency: String,
	val ratio: Int
)