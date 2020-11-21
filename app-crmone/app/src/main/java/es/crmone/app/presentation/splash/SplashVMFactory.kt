package es.crmone.app.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections

internal class SplashVMFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SplashViewModel(
        Injections.sessionRepository
    ) as T
}
