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

    private val _loading = MutableLiveData<Boolean>()
    private val _clientsLD = MutableLiveData<List<Client>>()
    private val _goToClientDetailLD = SingleLiveEvent<Int>()
    val clientsLD: LiveData<List<Client>> = _clientsLD
    val goToClientDetailLD: LiveData<Int> = _goToClientDetailLD
    val loading: LiveData<Boolean> = _loading

    fun loadClients() {
        _loading.value = true
        repository.getClients(object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _loading.value = false
                _clientsLD.value = clients.map { Client(it.id, it.cif, it.razonSocial) }
            }
            override fun onError() {
                _loading.value = false
            }

        })
    }

    fun loadClientsQuery(query: String) {
        _loading.value = true
        repository.getClientsQuery(query, object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _loading.value = false
                _clientsLD.value = clients.map { Client(it.id, it.cif, it.razonSocial) }

            }

            override fun onError() {
                _loading.value = false
            }
        })

    }
    fun seleccionar(client: Client) {
        _goToClientDetailLD.value = client.id
    }
}