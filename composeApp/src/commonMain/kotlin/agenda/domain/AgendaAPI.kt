package agenda.domain

import kotlinx.coroutines.flow.Flow

interface AgendaAPI {
    fun search(criteria: SearchAgendaCriteria): Flow<List<Agenda>>
    suspend fun searchV2(criteria: SearchAgendaCriteria): List<Agenda>
    suspend fun book(agendaId: String, hour: String, player: Player): Agenda
    suspend fun cancelBooking(agendaId: String, hourId: String, player: Player): Agenda
}

sealed interface SearchAgendaCriteria {
    class ByWeekAndYear(val week: Week, val year: Year): SearchAgendaCriteria
}