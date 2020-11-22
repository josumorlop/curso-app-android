package es.crmone.app.repository.report

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import es.crmone.app.repository.login.User

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteReportRepository(private val api: EndPoints = RetrofitService.endpoints) :
    ReportRepository {
    override fun insertReport(
        idClient: Int,
        user: User,
        observations: String,
        latitute: Double,
        longitude: Double,
        accuracy: Float,
        callback: ReportRepository.ReportCallback
    ) {
        val bodyRequest = ReportBodyRequest(idClient, user, observations, latitute, longitude, accuracy)
        api.insertReport(bodyRequest).enqueue(object: Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    val body: Boolean? = response.body()

                    if (body!=null) {
                        callback.onSuccess(true)
                    } else {
                        callback.onSuccess(false)
                    }
                } else {
                    callback.onSuccess(false)
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                callback.onSuccess(false)
            }
        })
    }

}