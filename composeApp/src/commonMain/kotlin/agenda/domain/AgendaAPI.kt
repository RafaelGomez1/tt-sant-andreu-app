package agenda.domain

import com.benasher44.uuid.Uuid

interface AgendaAPI {
    suspend fun search(criteria: SearchAgendaCriteria): List<Agenda>
    suspend fun book(agendaId: Uuid, hour: Uuid, player: Player)
    suspend fun cancelBooking(agendaId: Uuid, hourId: Uuid, player: Player)
}

sealed interface SearchAgendaCriteria {
    class ByWeekAndYear(val week: Week, val year: Year): SearchAgendaCriteria
}