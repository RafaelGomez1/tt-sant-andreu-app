package agenda.infrastructure.api

import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.Player
import agenda.domain.SearchAgendaCriteria
import agenda.domain.SearchAgendaCriteria.ByWeekAndYear
import agenda.domain.Week
import agenda.domain.Year
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shared.client.RestClient
import shared.client.RestClient.Companion.SERVER_URL

class RestAgendaAPI(private val client: RestAgendaClient) : AgendaAPI {
    override fun search(criteria: SearchAgendaCriteria): Flow<List<Agenda>> = flow {
        when (criteria) {
            is ByWeekAndYear -> emit(client.searchBy(criteria.week, criteria.year))
        }
    }

    override suspend fun book(agendaId: String, hour: String, player: Player): Agenda {
        TODO("Not yet implemented")
    }

    override suspend fun cancelBooking(agendaId: String, hourId: String, player: Player): Agenda {
        TODO("Not yet implemented")
    }

}

class RestAgendaClient(private val client: RestClient) {
    suspend fun searchBy(week: Week, year: Year): List<Agenda> =
        client.backend.get("$SERVER_URL/agendas") {
            url.parameters.append("week", week.toString())
            url.parameters.append("year", year.toString())
        }.body()
}
