package es.crmone.app.repository.report

import es.crmone.app.repository.login.User


interface ReportRepository {
    interface ReportCallback {
        fun onSuccess(success: Boolean)
        fun onError()
    }
    fun insertReport(
        idClient: Int,
        user: User,
        observations: String,
        latitute: Double,
        longitude: Double,
        accuracy: Float,
        callback: ReportCallback
    )
}