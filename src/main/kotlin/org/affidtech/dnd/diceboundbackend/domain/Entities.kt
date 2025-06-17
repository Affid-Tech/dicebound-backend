package org.affidtech.dnd.diceboundbackend.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

interface ScheduleSessionProjection {
	fun getSessionId(): UUID
	fun getAdventureId(): UUID
	fun getAdventureTitle(): String
	fun getType(): String
	fun getGameSystem(): String
	fun getDmName(): String
	fun getDmBio(): String?
	fun getStartTime(): Instant
	fun getDurationHours(): Int
	fun getMinPlayers(): Int
	fun getMaxPlayers(): Int
	fun getDescription(): String?
	fun getPlayersSignedUp(): Int
}

@Entity
class GameSessionEntity(
	@Id
	val id: UUID = UUID.randomUUID()
	// можно не добавлять остальные поля, если не нужны
)