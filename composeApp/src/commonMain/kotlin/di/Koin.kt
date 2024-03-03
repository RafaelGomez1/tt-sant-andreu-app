package di

import agenda.infrastructure.api.RestAgendaAPI
import agenda.infrastructure.api.RestAgendaClient
import shared.client.RestClient
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import agenda.infrastructure.ui.AgendaViewModel

private val sharedModule = module {
    singleOf(::RestClient)
}

private val agendaModule = module {
    singleOf(::RestAgendaAPI)
    singleOf(::RestAgendaClient)
    singleOf(::AgendaViewModel)
}

fun initKoin() {
    startKoin {
        modules(agendaModule)
        modules(sharedModule)
    }
}