package es.crmone.app.repository.client_info

interface ClientInfoRepository {
    interface ClientInfoCallback {
        fun onSuccess(clientInfo: ClientInfoDTO)
        fun onError()
    }
    fun getClientInfo(query: Int, callback: ClientInfoCallback)
}