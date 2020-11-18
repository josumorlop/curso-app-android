package es.crmone.app.presentation.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.repository.calendar.CalendarDTO

import es.crmone.app.repository.calendar.CalendarRepository



class CalendarViewModel(private val repository: CalendarRepository) : ViewModel() {
    private val _calendarLD = MutableLiveData<List<Calendar>>()
    val calendarLD: LiveData<List<Calendar>> = _calendarLD


    init {
        loadCalendar()
    }
    private fun loadCalendar() {
        repository.getCalendar(object: CalendarRepository.CalendarCallback {
            override fun onSuccess(calendar: List<CalendarDTO>) {
                _calendarLD.value = calendar.map {
                    Calendar(
                        it.id,
                        it.fecha,
                        it.hora,
                        it.comentarios
                    )
                }
            }
            override fun onError() {

            }
        })
    }
}