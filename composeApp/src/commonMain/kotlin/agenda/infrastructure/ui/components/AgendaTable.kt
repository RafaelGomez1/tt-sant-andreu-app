package agenda.infrastructure.ui.components

import agenda.domain.Agenda
import agenda.infrastructure.ui.AgendaEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AgendaTable(
    agenda: Agenda,
    onEvent: (AgendaEvent) -> Unit
) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            // Display Agenda details
            AgendaTableHeader()

            // Display Available Hours
            agenda.availableHours.forEach { hour ->
                AvailableHourRow(hour, onEvent)
            }
        }
    }
}

@Composable
fun AgendaTableHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Column {
            Text("Horas")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text("Socios")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text("Estado")
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text("Reservar")
        }
    }
}
