package agenda.infrastructure

import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.Player
import agenda.domain.SearchAgendaCriteria
import agenda.domain.SearchAgendaCriteria.ByWeekAndYear
import com.benasher44.uuid.Uuid
import io.ktor.client.call.body
import io.ktor.client.request.get
import shared.HttpClient.SERVER_URL
import shared.HttpClient.client

class RestAgendaAPI : AgendaAPI {
    override suspend fun search(criteria: SearchAgendaCriteria): List<Agenda> =
        when(criteria) {
            is ByWeekAndYear ->
                client.get("$SERVER_URL/agendas") {
                    url.parameters.append("week", criteria.week.toString())
                    url.parameters.append("year", criteria.year.toString())
            }.body()
        }

    override suspend fun book(agendaId: Uuid, hour: Uuid, player: Player) {
        TODO("Not yet implemented")
    }

    override suspend fun cancelBooking(agendaId: Uuid, hourId: Uuid, player: Player) {
        TODO("Not yet implemented")
    }
}