package es.crmone.app.repository.checkout

import es.crmone.app.repository.login.User

class CheckOutBodyRequest (
    val idCalendar: Int,
    val user: User,
    val observations: String,
    val latitute: Double,
    val longitude: Double,
    val accuracy: Float
)

