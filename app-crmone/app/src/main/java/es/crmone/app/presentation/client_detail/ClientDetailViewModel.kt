package es.crmone.app.presentation.client_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.calendar.CalendarRepository

class ClientDetailViewModel(private val idClient: Int, private val repository: CalendarRepository) : ViewModel() {
    private val _pendingsLD = MutableLiveData<Int>()
    private val _goToReport = SingleLiveEvent<Int>()
    val pendingsLD: LiveData<Int> = _pendingsLD
    val goToReportLD: LiveData<Int> = _goToReport

    fun update(pendings: Int) {
        _pendingsLD.value = pendings
    }
    fun report() {
        _goToReport.value = idClient
    }
}