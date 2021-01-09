package es.crmone.app.presentation.client_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.client_create.ClientCreateRepository
import es.crmone.app.repository.login.User
import es.crmone.app.repository.session.SessionRepository


class ClientCreateViewModel(private val ClientCreateRepository: ClientCreateRepository, private val sessionRepository: SessionRepository) : ViewModel() {

    //private val _loading = MutableLiveData<Boolean>()
    private  val _successInsert = MutableLiveData<Boolean>()

    //val loading: LiveData<Boolean> = _loading
    val successInsert: LiveData<Boolean> = _successInsert

    fun insertClient(cif: String, literal: String) {

        val user = sessionRepository.getUser()
        //query(user, cif, literal)
        _successInsert.value = false

    }

    private fun query(user: User?, cif: String, razonSocial: String) {

        if (user != null) {
            ClientCreateRepository.insertClient(user, cif, razonSocial, object : ClientCreateRepository.ClientCreateCallback  {
                override fun onSuccess(success: Boolean) {
                    if (success) {
                        _successInsert.value = success
                        //si se ha registrado correctamente...
                    } else {
                        //si el CIF es EXISTENTE...
                        _successInsert.value = false
                    }
                    //_loading.value = false

                }

                override fun onError() {
                    _successInsert.value = false
                    //_loading.value = true
                }

            })
        }

    }

}