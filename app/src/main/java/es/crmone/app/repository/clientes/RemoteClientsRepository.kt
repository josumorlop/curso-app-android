package es.crmone.app.repository.clientes

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteClientsRepository(private val api: EndPoints = RetrofitService.endpoints) : ClientsRepository {
    override fun getClients(callback: ClientsRepository.ClientsCallback) {
        api.getClients().enqueue(object : Callback<List<ClientDTO>> {
            override fun onResponse(call: Call<List<ClientDTO>>, response: Response<List<ClientDTO>>) {
                if (response.isSuccessful) {
                    val clients = response.body()
                    if (clients!=null) {
                        callback.onSuccess(clients)
                        return
                    }
                }
                callback.onError()
            }
            override fun onFailure(call: Call<List<ClientDTO>>, t: Throwable) {
                callback.onError()
            }
        })
    }

}