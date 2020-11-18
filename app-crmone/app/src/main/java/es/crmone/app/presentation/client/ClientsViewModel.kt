package es.crmone.app.presentation.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.repository.clientes.ClientDTO
import es.crmone.app.repository.clientes.ClientsRepository

class ClientsViewModel(private val repository: ClientsRepository) : ViewModel() {
    private val _clientsLD = MutableLiveData<List<Client>>()
    val clientsLD: LiveData<List<Client>> = _clientsLD


    init {
        loadClients()
    }
    private fun loadClients() {
        repository.getClients(object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _clientsLD.value = clients.map { Client(it.cif, it.razonSocial) }
            }
            override fun onError() {

            }
        })
    }

    fun loadClientsQuery(query: String) {

        repository.getClientsQuery(query, object: ClientsRepository.ClientsCallback {
            override fun onSuccess(clients: List<ClientDTO>) {
                _clientsLD.value = clients.map { Client(it.cif, it.razonSocial) }
            }

            override fun onError() {

            }
        })

    }
    fun seleccionar(client: Client) {

    }
}