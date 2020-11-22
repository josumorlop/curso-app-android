package es.crmone.app.presentation.client_detail.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.presentation.client.ClientInfo
import es.crmone.app.repository.client_info.ClientInfoDTO
import es.crmone.app.repository.client_info.ClientInfoRepository


class ClientInfoViewModel(private val idClient: Int, private val repository: ClientInfoRepository) : ViewModel() {
    private val _clientinfoLD = MutableLiveData<ClientInfo>()
    val clientinfoLD: LiveData<ClientInfo> = _clientinfoLD

    init {
        loadClientInfo()
    }
    fun loadClientInfo() {
        repository.getClientInfo(idClient, object : ClientInfoRepository.ClientInfoCallback {
            override fun onSuccess(clientInfo: ClientInfoDTO) {
                _clientinfoLD.value = ClientInfo(clientInfo.id, clientInfo.cif, clientInfo.razonSocial)
            }
            override fun onError() {
            }
        })
    }
}
