package es.crmone.app.presentation.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.calendar.CalendarDTO

import es.crmone.app.repository.calendar.CalendarRepository



class CalendarViewModel(private val repository: CalendarRepository) : ViewModel() {
    private val _calendarLD = MutableLiveData<List<CalendarOne>>()
    private val _goToCheckOut = SingleLiveEvent<Int>()
    private val _loading = MutableLiveData<Boolean>()

    val calendarLD: LiveData<List<CalendarOne>> = _calendarLD
    val goToCheckOut: LiveData<Int> = _goToCheckOut
    val loading: LiveData<Boolean> = _loading

    init {
        loadCalendar()
    }
    fun loadCalendar() {
        _loading.value = true
        repository.getCalendar(object: CalendarRepository.CalendarCallback {
            override fun onSuccess(calendar: List<CalendarDTO>) {

                _calendarLD.value = calendar.map {
                    CalendarOne(
                        it.id,
                        it.cliente,
                        it.usuarioRegistro,
                        it.checkin,
                        it.checkout,
                        it.fecha,
                        it.hora,
                        it.horaFin,
                        it.comentarios,
                        it.comentarios2,
                        it.permisoCheckOut
                    )
                }
                _loading.value = false
            }
            override fun onError() {
                _loading.value = false
            }
        })
    }
    fun seleccionar(calendar: CalendarOne) {
        _goToCheckOut.value = calendar.id
    }
}