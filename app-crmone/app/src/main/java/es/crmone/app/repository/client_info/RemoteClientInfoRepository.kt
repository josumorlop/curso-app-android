package es.crmone.app.repository.client_info

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteClientInfoRepository(private val api: EndPoints = RetrofitService.endpoints) : ClientInfoRepository {

    override fun getClientInfo(query: Int, callback: ClientInfoRepository.ClientInfoCallback) {
        api.getClientInfo(query).enqueue(object : Callback<ClientInfoDTO> {
            override fun onResponse(call: Call<ClientInfoDTO>, response: Response<ClientInfoDTO>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data!=null) {
                        callback.onSuccess(data)
                        return
                    }
                }
                callback.onError()
            }
            override fun onFailure(call: Call<ClientInfoDTO>, t: Throwable) {
                callback.onError()
            }


        })
    }



}