package es.crmone.app.repository.report

import es.crmone.app.repository.login.User

class ReportBodyRequest (
    val idClient: Int,
    val user: User,
    val observations: String,
    val latitute: Double,
    val longitude: Double,
    val accuracy: Float
)

