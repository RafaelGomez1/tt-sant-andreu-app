package agenda.application.book

import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.Player

class AgendaBooker(private val api: AgendaAPI) {
    suspend operator fun invoke(player: Player, agendaId: String, hourId: String): Agenda =
        api.book(agendaId, hourId, player)
}