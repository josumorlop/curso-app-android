package es.crmone.app.repository.client_create

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import es.crmone.app.repository.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteClientCreateRepository(private val api: EndPoints = RetrofitService.endpoints) :
    ClientCreateRepository {
    override fun insertClient(
        user: User,
        cif: String,
        razonSocial: String,
        callback: ClientCreateRepository.ClientCreateCallback
    ) {
        val bodyRequest = ClientCreateBodyRequest(user, cif, razonSocial)
        api.insertClient(bodyRequest).enqueue(object: Callback<ClientCreateDTO> {
            override fun onResponse(call: Call<ClientCreateDTO>, response: Response<ClientCreateDTO>) {

                if (response.isSuccessful) {
                    val data: ClientCreateDTO? = response.body()

                    if (data!=null) {
                        callback.onSuccess(data.success)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<ClientCreateDTO>, t: Throwable) {
                callback.onError()
            }
        })
    }

}