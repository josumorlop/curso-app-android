package es.crmone.app.presentation.client_detail.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections
import es.crmone.app.repository.calendar.CalendarRepository
import es.crmone.app.repository.clientes.ClientsRepository

internal class ReportsVMFactory(private val clientID: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ReportsViewModel(
        clientID, Injections.calendarRepository
    ) as T
}
