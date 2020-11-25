package es.crmone.app.presentation.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.login.User
import es.crmone.app.repository.report.ReportDTO
import es.crmone.app.repository.report.ReportRepository
import es.crmone.app.repository.session.SessionRepository


class CRMLocation(val latitute: Double, val longitude: Double, val accuracy: Float)

class ReportViewModel(private val idClient: Int, private val ReportRepository: ReportRepository, private val sessionRepository: SessionRepository) : ViewModel() {
    private val _askLocation = SingleLiveEvent<Unit>()
    private val _askPermisions = SingleLiveEvent<Unit>()
    private val _closeReport = SingleLiveEvent<Unit>()
    private val _showErrorLocation = SingleLiveEvent<Unit>()
    private val _loading = MutableLiveData<Boolean>()


    val askLocation: LiveData<Unit> = _askLocation
    val askPermisions: LiveData<Unit> = _askPermisions
    val closeReport: LiveData<Unit> = _closeReport
    val errorReport = SingleLiveEvent<String>() // LiveData
    val showErrorLocation: LiveData<Unit> = _showErrorLocation // LiveData
    val loading: LiveData<Boolean> = _loading



    fun checkIn() {
        _loading.value = true
        _askLocation.call()
    }
    fun permisionsNotGranted() {
        _askPermisions.call()
        loadingVisibleGone()
    }

    fun location(observations: String, latitute: Double, longitude: Double, accuracy: Float) {

        val location = CRMLocation(latitute, longitude, accuracy)
        val user = sessionRepository.getUser()


        queryTemporal(idClient, user, observations, location.latitute, location.longitude, location.accuracy)

    }
    fun locationIsDisable() {
        _showErrorLocation.call()
        loadingVisibleGone()
    }
    fun userDenyPermissions() {
        loadingVisibleGone()
    }


    fun loadingVisibleGone() {
        _loading.value = false
    }


    private fun queryTemporal(idClient: Int, user: User?, observations: String, latitute: Double, longitude: Double, accuracy: Float) {
        if (user != null) {

            ReportRepository.insertReport(idClient, user, observations, latitute, longitude, accuracy, object : ReportRepository.ReportCallback  {
                override fun onSuccess(success: Boolean) {
                    if (success) {
                        _closeReport.call()
                    } else {
                        errorReport.value = "Error"
                    }
                    _loading.value = false

                }


                override fun onError() {
                    errorReport.value = "Error"
                    _loading.value = true
                }

            })
        }
    }

}
