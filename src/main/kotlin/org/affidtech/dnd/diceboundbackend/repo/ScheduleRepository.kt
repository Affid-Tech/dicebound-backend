package org.affidtech.dnd.diceboundbackend.repo

import org.affidtech.dnd.diceboundbackend.domain.GameSessionEntity
import org.affidtech.dnd.diceboundbackend.domain.ScheduleSessionProjection
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface ScheduleRepository : CrudRepository<GameSessionEntity, UUID> {
	
	@Query(
		// Пример: для всех фильтров, можно сделать через @Query(nativeQuery = true)
		"""
        select
			gs.id as sessionId,
			a.id as adventureId,
			a.title as adventureTitle,
			a.type as type,
			a.game_system as gameSystem,
			u.name as dmName,
			u.bio as dmBio,
			gs.start_time as startTime,
			gs.duration_hours as durationHours,
			a.min_players as minPlayers,
			a.max_players as maxPlayers,
			a.description as description,
			(
				select count(*)
				from adventure_signup s
				where s.adventure_id = a.id
				  and s.status in ('PENDING', 'APPROVED')
			) as playersSignedUp
		from game_session gs
		join adventure a on a.id = gs.adventure_id
		join user_profile u on u.id = a.dm_id
		where gs.start_time > :from
		  and (:to = '9999-12-31T23:59:59+00:00' or gs.start_time < :to)
		  and (:system = '' or a.game_system = :system)
		  and (:dm = '' or lower(u.name) like lower(concat('%', :dm, '%')))
		  and (:adventureId = '00000000-0000-0000-0000-000000000000' or a.id = :adventureId)
		order by gs.start_time
        """,
		nativeQuery = true
	)
	fun findSchedule(
		@Param("from") from: OffsetDateTime,
		@Param("to") to: OffsetDateTime?,
		@Param("system") system: String?,
		@Param("dm") dm: String?,
		@Param("adventureId") adventureId: UUID?
	): List<ScheduleSessionProjection>
}
