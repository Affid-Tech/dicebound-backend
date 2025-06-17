package org.affidtech.dnd.diceboundbackend.web

import java.time.OffsetDateTime
import java.util.UUID

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
