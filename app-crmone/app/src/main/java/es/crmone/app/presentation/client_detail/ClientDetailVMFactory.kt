package es.crmone.app.presentation.client_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.repository.calendar.CalendarRepository
import es.crmone.app.repository.clientes.ClientsRepository

internal class ClientDetailVMFactory(private val clientID: Int, private val repository: CalendarRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClientDetailViewModel(
        clientID, repository
    ) as T
}
