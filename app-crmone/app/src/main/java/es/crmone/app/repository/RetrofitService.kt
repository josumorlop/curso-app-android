package es.crmone.app.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoints: EndPoints
    init {
        val builder: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.crmone.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        endpoints = builder.create(EndPoints::class.java)
    }
}