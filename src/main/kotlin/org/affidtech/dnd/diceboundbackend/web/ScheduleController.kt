package org.affidtech.dnd.diceboundbackend.web

import io.swagger.v3.oas.annotations.Operation
import org.affidtech.dnd.diceboundbackend.service.ScheduleService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime
import java.util.*

@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(
	private val scheduleService: ScheduleService
) {
	@Operation(summary = "Получить расписание игровых сессий")
	@GetMapping
	fun getSchedule(
		@RequestParam(required = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		from: OffsetDateTime?,
		@RequestParam(required = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		to: OffsetDateTime?,
		@RequestParam(required = false) system: String?,
		@RequestParam(required = false) dm: String?,
		@RequestParam(required = false) adventureId: UUID?
	): List<ScheduleSessionDto> {
		return scheduleService.getSchedule(
			from = from ?: OffsetDateTime.now(),
			to = to,
			system = system,
			dm = dm,
			adventureId = adventureId
		)
	}
}
