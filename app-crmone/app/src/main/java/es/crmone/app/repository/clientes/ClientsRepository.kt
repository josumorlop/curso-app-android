package es.crmone.app.repository.clientes

interface ClientsRepository {
    interface ClientsCallback {
        fun onSuccess(clients: List<ClientDTO>)
        fun onError()
    }
    fun getClients(callback: ClientsCallback)
}