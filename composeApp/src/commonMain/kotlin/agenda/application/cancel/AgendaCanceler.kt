package agenda.application.cancel

import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.Player

class AgendaCanceler(private val api: AgendaAPI) {
    suspend operator fun invoke(player: Player, agendaId: String, hourId: String): Agenda =
        api.cancelBooking(agendaId, hourId, player)
}