package es.crmone.app.presentation.client_detail.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections


internal class ClientInfoVMFactory(private val clientID: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClientInfoViewModel(
        clientID, Injections.clientInfoRepository
    ) as T
}
