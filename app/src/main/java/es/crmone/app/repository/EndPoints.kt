package es.crmone.app.repository

import es.crmone.app.repository.clientes.ClientDTO
import es.crmone.app.models.User
import es.crmone.app.presentation.login.LoginBodyRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPoints {
    @POST("login")
    fun login(@Body body: LoginBodyRequest): Call<User>
    @GET("clients")
    fun getClients(): Call<List<ClientDTO>>
}