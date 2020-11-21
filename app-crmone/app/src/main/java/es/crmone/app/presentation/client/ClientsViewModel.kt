package es.crmone.app.presentation.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.clientes.ClientDTO
import es.crmone.app.repository.clientes.ClientsRepository
import es.crmone.app.repository.session.SessionRepository

class ClientsViewModel(private val repository: ClientsRepository,
                       private val sessionRepository: SessionRepository) : ViewModel() {
    private val _clientsLD = MutableLiveData<List<Client>>()
    private val _goToClientDetailLD = SingleLiveEvent<Int>()
    val clientsLD: LiveData<List<Client>> = _clientsLD
    val goToClientDetailLD: LiveData<Int> = _goToClientDetailLD


    init {
        loadClients()
    }
    private fun loadClients() {
        repository.getClients(object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _clientsLD.value = clients.map { Client(it.id, it.cif, it.razonSocial) }
            }
            override fun onError() {

            }
        })
    }

    fun loadClientsQuery(query: String) {

        repository.getClientsQuery(query, object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _clientsLD.value = clients.map { Client(it.id, it.cif, it.razonSocial) }
            }

            override fun onError() {

            }
        })

    }
    fun seleccionar(client: Client) {
        _goToClientDetailLD.value = client.id
    }
}