package es.crmone.app.presentation.login

import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.models.User
import es.crmone.app.repository.RetrofitService
import es.crmone.app.repository.login.LoginBodyRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

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

        //loginSuccess.value = true
        queryTemporal(email, password)

    }


    private fun queryTemporal(email: String, password: String) {

        val bodyRequest = LoginBodyRequest(email, password)
        RetrofitService.endpoints.login(bodyRequest).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body!=null) {
//                        body.profile.crm
                        loginSuccess.value = true
                    }
                } else {
                    loginSuccess.value = false
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loginSuccess.value = false
            }

        })

    }


}