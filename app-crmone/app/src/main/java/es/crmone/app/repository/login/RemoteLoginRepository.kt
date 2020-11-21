package es.crmone.app.repository.login

import es.crmone.app.models.User
import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteLoginRepository(private val api: EndPoints = RetrofitService.endpoints) : LoginRepository {
    override fun login(
        email: String,
        password: String,
        callback: LoginRepository.LoginCallback
    ) {
        val bodyRequest = LoginBodyRequest(email, password)
        api.login(bodyRequest).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body!=null) {
                        callback.onSuccess(true)
                    } else {
                        callback.onSuccess(false)
                    }
                } else {
                    callback.onSuccess(false)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onSuccess(false)
            }
        })
    }

}