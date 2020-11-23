package es.crmone.app.repository

import es.crmone.app.repository.calendar.CalendarDTO
import es.crmone.app.repository.client_info.ClientInfoDTO
import es.crmone.app.repository.clientes.ClientDTO
import es.crmone.app.repository.login.LoginBodyRequest
import es.crmone.app.repository.login.User
import es.crmone.app.repository.report.ReportBodyRequest
import es.crmone.app.repository.report.ReportDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EndPoints {
    @POST("login")
    fun login(@Body body: LoginBodyRequest): Call<User>

    @GET("clients/{query}")
    fun getClientsQuery(@Path("query") query: String?): Call<List<ClientDTO>>

    @GET("clientInfo/{query}")
    fun getClientInfo(@Path("query") query: Int): Call<ClientInfoDTO>

    @GET("clients")
    fun getClients(): Call<List<ClientDTO>>

    @GET("calendar")
    fun getCalendar(): Call<List<CalendarDTO>>

    @GET("calendar/{query}")
    fun getCalendar(@Path("query") idClient: Int): Call<List<CalendarDTO>>

    @POST("report")
    fun insertReport(@Body body: ReportBodyRequest): Call<ReportDTO>

}
