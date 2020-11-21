package es.crmone.app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections
import es.crmone.app.repository.calendar.CalendarRepository
import es.crmone.app.repository.clientes.ClientsRepository
import es.crmone.app.repository.login.LoginRepository
import es.crmone.app.repository.login.RemoteLoginRepository

internal class LoginVMFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(
        Injections.loginRepository
    ) as T
}
