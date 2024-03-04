package agenda.infrastructure.ui

import agenda.application.book.AgendaBooker
import agenda.application.cancel.AgendaCanceler
import agenda.application.search.AgendaSearcher
import agenda.domain.Agenda
import agenda.domain.AgendaAPI
import agenda.domain.AvailableHour
import agenda.domain.Day
import agenda.domain.HourType
import agenda.domain.MaxCapacity
import agenda.domain.Player
import agenda.infrastructure.ui.AgendaEvent.BookHour
import agenda.infrastructure.ui.AgendaEvent.CancelBooking
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent

class AgendaViewModel(
    api: AgendaAPI
) : ViewModel() {

    private val searchAgenda = AgendaSearcher(api)
    private val bookAgenda = AgendaBooker(api)
    private val cancelAgenda = AgendaCanceler(api)

    private val internalState = MutableStateFlow(AgendaViewModelState())

    init {
        viewModelScope.launch {
            val state = internalState.value
            val initialAgendas = searchAgenda.invoke2(state.year, state.week)
            internalState.update { it.copy(agendas = initialAgendas) }
        }
    }

    val state = internalState.asStateFlow()

    fun onEvent(event: AgendaEvent) {
        when(event) {
            is BookHour -> {
                viewModelScope.launch {
                    internalState.value.selectedHour?.let { hour ->
                        bookAgenda(event.player, event.agendaId, hour.id)
                        delay(300L) // Animation delay
                        internalState.update { it.copy(selectedHour = null) }
                    }
                }
            }
            is CancelBooking -> {
                viewModelScope.launch {
                    internalState.value.selectedHour?.let { hour ->
                        cancelAgenda(event.player, event.agendaId, hour.id)
                        delay(300L) // Animation delay
                        internalState.update { it.copy(selectedHour = null) }
                    }
                }
            }
        }
    }
}

sealed interface AgendaEvent {
    class BookHour(val player: Player, val agendaId: String): AgendaEvent
    class CancelBooking(val player: Player, val agendaId: String): AgendaEvent
}

data class AgendaViewModelState(
    val agendas: List<Agenda> = emptyList(),
    val today: Instant = Clock.System.now(),
    val selectedHour: AvailableHour? = null,
    val week: Int = today.toWeekNumber(),
    val year: Int = today.toYear()
)
private fun Instant.toYear(): Int = this.toLocalDateTime(TimeZone.UTC).date.year
private fun Instant.toWeekNumber(): Int= (this.toLocalDateTime(TimeZone.UTC).date.dayOfYear / 7)