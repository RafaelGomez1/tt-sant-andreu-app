package agenda.application.search

import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.SearchAgendaCriteria.ByWeekAndYear
import agenda.domain.Week
import agenda.domain.Year
import kotlinx.coroutines.flow.Flow

class AgendaSearcher(private val api: AgendaAPI) {
    operator fun invoke(year: Year, week: Week): Flow<List<Agenda>> =
        api.search(ByWeekAndYear(week, year))

    suspend fun invoke2(year: Year, week: Week): List<Agenda> =
        api.searchV2(ByWeekAndYear(week, year))
            .filter { it.availableHours.isNotEmpty() }
}
