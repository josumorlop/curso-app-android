package es.crmone.app.di

import es.crmone.app.repository.calendar.RemoteCalendarRepository
import es.crmone.app.repository.clientes.RemoteClientsRepository
import es.crmone.app.repository.login.RemoteLoginRepository

object Injections {
    val loginRepository by lazy { RemoteLoginRepository() }
    val calendarRepository by lazy { RemoteCalendarRepository() }
    val clientRepository by lazy { RemoteClientsRepository() }
}