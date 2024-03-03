package agenda.infrastructure.ui.components

import agenda.domain.AvailableHour
import agenda.infrastructure.ui.AgendaEvent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*

@Composable
fun AvailableHourRow(
    hour: AvailableHour,
    onEvent: (AgendaEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(text = "${hour.from} - ${hour.to}")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            hour.registeredPlayers.forEach { Text(it.name) }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = if (hour.isAtMaxCapacity()) "Lleno" else "Disponible")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons for actions
        Column {
            if (hour.isNotAtMaxCapacity()) {
                Button(onClick = { /* Handle Add action */ }) {
                    Text(text = "Add")
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (hour.isNotEmpty()) {
                Button(onClick = { /* Handle Remove action */ }) {
                    Text(text = "Remove")
                }
            }
        }



    }
}
