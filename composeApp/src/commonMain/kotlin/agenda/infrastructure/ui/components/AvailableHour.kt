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
        // Show "From - To" column
        Text(text = "${hour.from} - ${hour.to}")

        Spacer(modifier = Modifier.width(16.dp))

        // Show registered players
        Text(text = "Registered Players: ${hour.registeredPlayers.joinToString { it.name }}")

        Spacer(modifier = Modifier.width(16.dp))

        // Show availability status
        Text(text = if (hour.registeredPlayers.size < hour.capacity.value) "Disponible" else "Lleno")

        Spacer(modifier = Modifier.weight(1f))

        // Buttons for actions
        Button(onClick = { /* Handle Add action */ }) {
            Text(text = "Add")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = { /* Handle Remove action */ }) {
            Text(text = "Remove")
        }
    }
}
