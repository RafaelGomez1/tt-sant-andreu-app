package agenda.infrastructure.ui.components

import agenda.domain.Agenda
import agenda.infrastructure.ui.AgendaEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AgendaTable(
    agendas: List<Agenda>,
    onEvent: (AgendaEvent) -> Unit
) {
    LazyColumn {
        items(agendas) { agenda ->
            Column(modifier = Modifier.padding(8.dp)) {
                // Display Agenda details
                Text(text = "Day: ${agenda.day}, Month: ${agenda.month}, Week: ${agenda.week}, Year: ${agenda.year}")

                // Display Available Hours
                agenda.availableHours.forEach { hour ->
                    AvailableHourRow(hour, onEvent)
                }
            }
        }
    }
}
