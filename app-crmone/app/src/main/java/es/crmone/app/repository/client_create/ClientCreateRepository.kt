package es.crmone.app.repository.client_create

import es.crmone.app.repository.login.User

interface ClientCreateRepository {
    interface ClientCreateCallback {
        fun onSuccess(success: Boolean)
        fun onError()
    }
    fun insertClient(
        user: User,
        cif: String,
        razonSocial: String,
        callback: ClientCreateCallback
    )
}