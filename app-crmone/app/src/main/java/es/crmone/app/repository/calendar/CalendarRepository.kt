package es.crmone.app.repository.calendar



interface CalendarRepository {
    interface CalendarCallback {
        fun onSuccess(calendar: List<CalendarDTO>)
        fun onError()
    }
    fun getCalendar(callback: CalendarCallback)
    fun getCalendar(idClient: Int, callback: CalendarCallback)
}