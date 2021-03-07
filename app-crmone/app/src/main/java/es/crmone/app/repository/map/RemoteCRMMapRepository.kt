package es.crmone.app.repository.map

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteCRMMapRepository(private val api: EndPoints = RetrofitService.endpoints) : CRMMapRepository {
    override suspend fun getCheckPoint(): CheckPointOperation {
        val response = api.getCheckPoint()
        if (response.isSuccessful) {
            val body = response.body()
            if (body!=null) {
                return CheckPointOperation.Success(body)
            } else {
                return CheckPointOperation.Error
            }

        } else {
            return CheckPointOperation.Error
        }
    }
}

