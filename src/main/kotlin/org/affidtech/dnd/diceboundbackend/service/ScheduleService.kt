package org.affidtech.dnd.diceboundbackend.service

import org.affidtech.dnd.diceboundbackend.domain.ScheduleSessionProjection
import org.affidtech.dnd.diceboundbackend.repo.ScheduleRepository
import org.affidtech.dnd.diceboundbackend.web.DmShortDto
import org.affidtech.dnd.diceboundbackend.web.ScheduleSessionDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class ScheduleService(
	private val scheduleRepository: ScheduleRepository,
	@Value("\${telegram.signup-username}") private val signupUsername: String
) {
	fun getSchedule(
		from: OffsetDateTime = OffsetDateTime.now(),
		to: OffsetDateTime? = null,
		system: String? = null,
		dm: String? = null,
		adventureId: UUID? = null
	): List<ScheduleSessionDto> {
		val actualTo = to ?: OffsetDateTime.parse("9999-12-31T23:59:59+00:00")
		val actualSystem = system ?: ""
		val actualDm = dm ?: ""
		val actualAdventureId = adventureId ?: UUID.fromString("00000000-0000-0000-0000-000000000000")
		return scheduleRepository.findSchedule(from, actualTo, actualSystem, actualDm, actualAdventureId)
			.map { session ->
				val placesLeft = session.getMaxPlayers() - session.getPlayersSignedUp()
				val startTime = session.getStartTimeAsOffsetDateTime()
				val signupLink = if (placesLeft > 0) {
					buildSignupLink(
						username = signupUsername,
						title = session.getAdventureTitle(),
						startTime = startTime
					)
				} else {
					null
				}
				ScheduleSessionDto(
					sessionId = session.getSessionId(),
					adventureId = session.getAdventureId(),
					adventureTitle = session.getAdventureTitle(),
					type = session.getType(),
					gameSystem = session.getGameSystem(),
					dm = DmShortDto(
						name = session.getDmName(),
						bio = session.getDmBio()
					),
					startTime = startTime,
					durationHours = session.getDurationHours(),
					minPlayers = session.getMinPlayers(),
					maxPlayers = session.getMaxPlayers(),
					description = session.getDescription(),
					signupLink = signupLink
				)
			}
	}
	
	private fun buildSignupLink(username: String, title: String, startTime: OffsetDateTime): String {
		val formattedDate = startTime.format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
		val message = "Привет! Хочу записаться на игру «$title» ($formattedDate)."
		val encoded = URLEncoder.encode(message, StandardCharsets.UTF_8)
		return "https://t.me/$username?text=$encoded"
	}
	
	private fun ScheduleSessionProjection.getStartTimeAsOffsetDateTime(): OffsetDateTime = OffsetDateTime.ofInstant(getStartTime(), ZoneOffset.UTC)
}
