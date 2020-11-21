package es.crmone.app.presentation.login

import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.login.LoginRepository
import es.crmone.app.repository.login.User
import es.crmone.app.repository.session.SessionRepository


class LoginViewModel(private val loginRepository: LoginRepository,
                     private val sessionRepository: SessionRepository) : ViewModel() {

    val loginSuccess = SingleLiveEvent<Boolean>() // LiveData


    fun login(email: String, password: String) {

        if (email.isEmpty()) {
            loginSuccess.value = false
            return
        }

        if (password.isEmpty()) {
            loginSuccess.value = false
            return
        }

        queryTemporal(email, password)

    }


    private fun queryTemporal(email: String, password: String) {
        loginRepository.login(email, password, object : LoginRepository.LoginCallback {
            override fun onSuccess(success: Boolean, user: User?) {
                if (success && user!=null) {
                    sessionRepository.save(user)
                }
                loginSuccess.value = success
            }

            override fun onError() {
                loginSuccess.value = false
            }

        })
    }

}