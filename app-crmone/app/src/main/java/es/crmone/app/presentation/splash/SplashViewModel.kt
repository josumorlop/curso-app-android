package es.crmone.app.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.session.SessionRepository


class SplashViewModel(private val sessionRepository: SessionRepository) : ViewModel() {
    val loadSplashLD = SingleLiveEvent<String>()
    val userLoggedLD = SingleLiveEvent<Boolean>()
    init {
        Handler(Looper.getMainLooper()).postDelayed({
            loadSplashLD.value = ""
        }, 1000)
        loadUser()
    }
    private fun loadUser() {
        userLoggedLD.value = sessionRepository.isUserLogged()
    }
}
