package es.crmone.app.presentation.client_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections



internal class ClientCreateVMFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClientCreateViewModel(
        Injections.clientCreateRepository, Injections.sessionRepository
    ) as T
}
