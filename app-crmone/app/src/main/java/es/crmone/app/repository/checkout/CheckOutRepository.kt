package es.crmone.app.repository.checkout

interface CheckOutRepository {
    interface CheckOutCallback {
        fun onSuccess(success: Boolean)
        fun onError()
    }
    fun insertCheckOut(
        idCalendar: Int,
        observations: String,
        latitute: Double,
        longitude: Double,
        accuracy: Float,
        callback: CheckOutCallback
    )
}