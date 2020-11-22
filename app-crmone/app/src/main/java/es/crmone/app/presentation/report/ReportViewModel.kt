package es.crmone.app.presentation.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.session.SessionRepository

class CRMLocation(val latitute: Double, val longitude: Double, val accuracy: Float)

class ReportViewModel(private val sessionRepository: SessionRepository) : ViewModel() {
    private val _askLocation = SingleLiveEvent<Unit>()
    private val _askPermisions = SingleLiveEvent<Unit>()
    private val _closeReport = SingleLiveEvent<Unit>()
    val askLocation: LiveData<Unit> = _askLocation
    val askPermisions: LiveData<Unit> = _askPermisions
    val closeReport: LiveData<Unit> = _closeReport

    fun checkIn() {
        _askLocation.call()
    }
    fun permisionsNotGranted() {
        _askPermisions.call()
    }
    fun location(observations: String, latitute: Double, longitude: Double, accuracy: Float) {
        val location = CRMLocation(latitute, longitude, accuracy)
        val user = sessionRepository.getUser()
        //hacer la llamada al api rest
        //si la llamada al servicio va bien: invocar closeReport.call()
    }
    fun userDenyPermissions() {

    }
}
