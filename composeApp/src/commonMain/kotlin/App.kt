import agenda.domain.SearchAgendaCriteria.ByWeekAndYear
import agenda.infrastructure.api.RestAgendaAPI
import agenda.infrastructure.api.RestAgendaClient
import agenda.infrastructure.ui.AgendaViewModel
import agenda.infrastructure.ui.components.AgendaListScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.initKoin
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import shared.client.RestClient

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    initKoin()

    val mainScope = MainScope()

    val restClientClient = RestClient()
    val agendaClient = RestAgendaClient(restClientClient)
    val api = RestAgendaAPI(agendaClient)

    mainScope.launch {
        api.search(ByWeekAndYear(week = 9, year = 2024)).collect { agendas ->
            agendas.map { println(it) }
        }
    }

    MaterialTheme {
        val viewModel = getViewModel(
            key = "agendas-view-screen",
            factory = viewModelFactory {
                AgendaViewModel(api)
            }
        )
        val state by viewModel.state.collectAsState()

        AgendaListScreen(state, viewModel::onEvent)
    }
}