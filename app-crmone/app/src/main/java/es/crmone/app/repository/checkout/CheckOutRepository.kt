package es.crmone.app.repository.checkout

import es.crmone.app.repository.login.User

interface CheckOutRepository {
    interface CheckOutCallback {
        fun onSuccess(success: Boolean)
        fun onError()
    }
    fun insertCheckOut(
        idCalendar: Int,
        user: User,
        observations: String,
        latitute: Double,
        longitude: Double,
        accuracy: Float,
        callback: CheckOutCallback
    )
}