package agenda.infrastructure.ui.components

import agenda.infrastructure.ui.AgendaEvent
import agenda.infrastructure.ui.AgendaViewModelState
import androidx.compose.runtime.Composable

@Composable
fun AgendaListScreen(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    AgendaTable(state.agendas, onEvent)
}