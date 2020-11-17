package es.crmone.app.presentation.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.repository.clientes.ClientsRepository

internal class ClientVMFactory(private val clientsRepository: ClientsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClientsViewModel(
        clientsRepository
    ) as T
}
