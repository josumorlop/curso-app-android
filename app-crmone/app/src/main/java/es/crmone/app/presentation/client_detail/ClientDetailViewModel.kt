package es.crmone.app.presentation.client_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.repository.calendar.CalendarRepository

class ClientDetailViewModel(private val idClient: Int, private val repository: CalendarRepository) : ViewModel() {
    private val _pendingsLD = MutableLiveData<Int>()
    val pendingsLD: LiveData<Int> = _pendingsLD

    fun update(pendings: Int) {
        _pendingsLD.value = pendings
    }
}