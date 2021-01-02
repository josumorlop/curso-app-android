package es.crmone.app.repository.checkout

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RemoteCheckOutRepository(private val api: EndPoints = RetrofitService.endpoints) :
    CheckOutRepository {
    override fun insertCheckOut(
        idCalendar: Int,
        observations: String,
        latitute: Double,
        longitude: Double,
        accuracy: Float,
        callback: CheckOutRepository.CheckOutCallback
    ) {
        val bodyRequest = CheckOutBodyRequest(idCalendar, observations, latitute, longitude, accuracy)
        api.insertCheckOut(bodyRequest).enqueue(object: Callback<CheckOutDTO> {
            override fun onResponse(call: Call<CheckOutDTO>, response: Response<CheckOutDTO>) {

                if (response.isSuccessful) {
                    val data: CheckOutDTO? = response.body()

                    if (data!=null) {
                        callback.onSuccess(data.success)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<CheckOutDTO>, t: Throwable) {
                callback.onError()
            }
        })
    }

}