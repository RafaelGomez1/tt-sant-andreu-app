import agenda.domain.SearchAgendaCriteria
import agenda.domain.SearchAgendaCriteria.ByWeekAndYear
import agenda.infrastructure.api.RestAgendaAPI
import agenda.infrastructure.api.RestAgendaClient
import agenda.infrastructure.ui.AgendaViewModel
import agenda.infrastructure.ui.components.AgendaListScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.initKoin
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import shared.client.RestClient

@Composable
fun App() {
    initKoin()

    val mainScope = MainScope()

    val restClientClient = RestClient()
    val agendaClient = RestAgendaClient(restClientClient)
    val api = RestAgendaAPI(agendaClient)

    val viewModel = getViewModel(
        key = Unit,
        factory = viewModelFactory {
            AgendaViewModel(api)
        }
    )

    MaterialTheme {
        val state by viewModel.state.collectAsState()
        AgendaListScreen(state, viewModel::onEvent)
    }
}