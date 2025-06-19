package org.affidtech.dnd.diceboundbackend.repo

import org.affidtech.dnd.diceboundbackend.domain.AdventureAvailableProjection
import org.affidtech.dnd.diceboundbackend.domain.AdventureEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AdventureAvailableRepository : CrudRepository<AdventureEntity, UUID> {
	
	@Query(
		"""
        select
            a.id as adventureId,
            a.title as title,
            a.type as type,
            a.cover_url as coverUrl,
            a.game_system as gameSystem,
            u.name as dmName,
            u.bio as dmBio,
            a.description as description,
            a.price_units as priceUnits,
            a.min_players as minPlayers,
            a.max_players as maxPlayers,
            (
                select count(*)
                from adventure_signup s
                where s.adventure_id = a.id and s.status in ('PENDING','APPROVED')
            ) as signedUp,
            a.max_players - (
                select count(*)
                from adventure_signup s
                where s.adventure_id = a.id and s.status in ('PENDING','APPROVED')
            ) as freeSeats
        from adventure a
        join user_profile u on u.id = a.dm_id
        where a.max_players - (
            select count(*)
            from adventure_signup s
            where s.adventure_id = a.id and s.status in ('PENDING','APPROVED')
        ) > 0 AND a.status = 'RECRUITING'
        """, nativeQuery = true
	)
	fun findAllAvailableAdventures(): List<AdventureAvailableProjection>
}
