package es.crmone.app.repository.checkout

class CheckOutBodyRequest (
    val idCalendar: Int,
    val observations: String,
    val latitute: Double,
    val longitude: Double,
    val accuracy: Float
)

