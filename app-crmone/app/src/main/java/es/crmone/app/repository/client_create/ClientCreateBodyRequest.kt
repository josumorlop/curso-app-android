package es.crmone.app.repository.client_create

import es.crmone.app.repository.login.User

class ClientCreateBodyRequest (
    val user: User,
    val cif: String,
    val razonSocial: String
)