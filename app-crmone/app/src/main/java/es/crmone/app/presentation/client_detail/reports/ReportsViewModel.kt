package es.crmone.app.presentation.client_detail.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.presentation.calendar.CalendarOne
import es.crmone.app.repository.calendar.CalendarDTO
import es.crmone.app.repository.calendar.CalendarRepository

class ReportsViewModel(private val idClient: Int, private val repository: CalendarRepository) : ViewModel() {
    private val _reportsLD = MutableLiveData<List<CalendarOne>>()
    private val _pendingsLD = MutableLiveData<Int>()
    private val _goToCheckOut = SingleLiveEvent<Int>()
    private val _loading = MutableLiveData<Boolean>()


    val reportsLD: LiveData<List<CalendarOne>> = _reportsLD
    val pendingsLD: LiveData<Int> = _pendingsLD
    val goToCheckOut: LiveData<Int> = _goToCheckOut
    val loading: LiveData<Boolean> = _loading

    fun loadCalendar() {
        _loading.value = true
        repository.getCalendar(idClient, object : CalendarRepository.CalendarCallback {
            override fun onSuccess(calendar: List<CalendarDTO>) {
                _loading.value = false
                _reportsLD.value = calendar.map { CalendarOne(it.id, it.cliente, it.usuarioRegistro, it.checkin, it.checkout, it.fecha, it.hora, it.horaFin, it.comentarios, it.comentarios2, it.permisoCheckOut) }
                _pendingsLD.value = 4
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
