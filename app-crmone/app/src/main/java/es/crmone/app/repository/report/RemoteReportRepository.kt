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
        api.insertReport(bodyRequest).enqueue(object: Callback<ReportDTO> {
            override fun onResponse(call: Call<ReportDTO>, response: Response<ReportDTO>) {

                if (response.isSuccessful) {
                    val data: ReportDTO? = response.body()

                    if (data!=null) {
                        callback.onSuccess(data.success)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<ReportDTO>, t: Throwable) {
                callback.onError()
            }
        })
    }

}