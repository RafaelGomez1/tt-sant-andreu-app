package di

import agenda.infrastructure.RestAgendaAPI
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val appModule = module {
    singleOf(::RestAgendaAPI)
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}