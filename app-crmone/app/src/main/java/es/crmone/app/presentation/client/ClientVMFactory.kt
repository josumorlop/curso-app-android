package es.crmone.app.presentation.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections
import es.crmone.app.repository.clientes.ClientsRepository

internal class ClientVMFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClientsViewModel(
        Injections.clientRepository, Injections.sessionRepository
    ) as T
}
