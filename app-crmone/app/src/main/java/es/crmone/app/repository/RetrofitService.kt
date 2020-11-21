package es.crmone.app.repository

import es.crmone.app.di.Injections
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoints: EndPoints
    init {
        val builder: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.crmone.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("X-Platform", "Android")
                        builder.header("X-Auth-Token", Injections.sessionRepository.getUser()?.id?:"notlogged")
                        return@Interceptor chain.proceed(builder.build())
                    }
                )
            }.build())
            .build()
        endpoints = builder.create(EndPoints::class.java)
    }
}