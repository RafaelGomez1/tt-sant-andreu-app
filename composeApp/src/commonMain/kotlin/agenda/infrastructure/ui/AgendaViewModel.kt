package agenda.infrastructure.ui

import agenda.application.book.AgendaBooker
import agenda.application.cancel.AgendaCanceler
import agenda.application.search.AgendaSearcher
import agenda.domain.AgendaAPI
import agenda.domain.Player
import agenda.infrastructure.ui.AgendaEvent.BookHour
import agenda.infrastructure.ui.AgendaEvent.CancelBooking
import androidx.compose.animation.expandHorizontally
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgendaViewModel(
    api: AgendaAPI
) : ViewModel() {

    private val searchAgenda = AgendaSearcher(api)
    private val bookAgenda = AgendaBooker(api)
    private val cancelAgenda = AgendaCanceler(api)

    private val internalState = MutableStateFlow(AgendaViewModelState())

    val state = combine(
        internalState,
        searchAgenda(9, 24)
    ) { state, agendas -> state.copy(agendas = agendas) }
        .stateIn(viewModelScope, WhileSubscribed(5000L), AgendaViewModelState())

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