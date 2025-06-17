package org.affidtech.dnd.diceboundbackend.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.time.OffsetDateTime
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

@Entity
class AdventureEntity(
	@Id
	val id: UUID = UUID.randomUUID()
	// можно не добавлять остальные поля, если не нужны
)

interface AdventureAvailableProjection {
	fun getAdventureId(): UUID
	fun getTitle(): String
	fun getGameSystem(): String
	fun getDmName(): String
	fun getDmBio(): String?
	fun getDescription(): String?
	fun getPriceUnits(): Int?
	fun getMinPlayers(): Int
	fun getMaxPlayers(): Int
	fun getSignedUp(): Int
	fun getFreeSeats(): Int
}

@Entity
@Table(name = "currency_rate")
data class CurrencyRateEntity(
	@Id
	@Column(length = 3)
	val currency: String,
	
	@Column(name = "ratio", nullable = false)
	var ratio: Int,
	
	@Column(name = "updated_at", nullable = false)
	var updatedAt: OffsetDateTime? = null,
)