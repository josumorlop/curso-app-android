package es.crmone.app.presentation.login

import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.common.capitalizeFirstLetter
import es.crmone.app.models.User
import es.crmone.app.repository.RetrofitService
import es.crmone.app.repository.login.LoginBodyRequest
import es.crmone.app.repository.login.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

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
            override fun onSuccess(success: Boolean) {
                loginSuccess.value = success
            }

            override fun onError() {
                loginSuccess.value = false
            }

        })
    }

}