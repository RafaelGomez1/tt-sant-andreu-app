package agenda.infrastructure.ui

import agenda.domain.Agenda
import agenda.domain.AvailableHour
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.TimeZone.Companion.UTC
import kotlinx.datetime.toLocalDateTime

data class AgendaViewModelState(
    val agendas: List<Agenda> = emptyList(),
    val today: Instant = Clock.System.now(),
    val selectedHour: AvailableHour? = null,
    val week: Int = today.toWeekNumber(),
    val year: Int = today.toYear()
)

fun Instant.toYear(): Int = this.toLocalDateTime(UTC).date.year
fun Instant.toWeekNumber(): Int= (this.toLocalDateTime(UTC).date.dayOfYear / 7)