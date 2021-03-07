package es.crmone.app.repository.map

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteCRMMapRepository(private val api: EndPoints = RetrofitService.endpoints) : CRMMapRepository {
    override fun getCheckPoint(callback: CRMMapRepository.CRMMapCallBack) {
        api.getCheckPoint().enqueue(object : Callback<List<CRMMapDTO>> {
            override fun onResponse(call: Call<List<CRMMapDTO>>, response: Response<List<CRMMapDTO>>) {
                if (response.isSuccessful) {
                    val checkPoints = response.body()
                    if (checkPoints!=null) {
                        callback.onSuccess(checkPoints)
                        return
                    }
                }
                callback.onError()
            }
            override fun onFailure(call: Call<List<CRMMapDTO>>, t: Throwable) {
                callback.onError()
            }
        })
    }
}

