package agenda.infrastructure.ui.components

import agenda.infrastructure.ui.AgendaEvent
import agenda.infrastructure.ui.AgendaViewModelState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun AgendaListScreen(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    // Add Club Logo above the tables
    LazyColumn {
        items(state.agendas) {agenda ->
            AgendaTable(agenda, onEvent)
        }
    }
}