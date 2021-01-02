package es.crmone.app.presentation.check_out

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent
import es.crmone.app.repository.checkout.CheckOutRepository


class CRMLocation(val latitute: Double, val longitude: Double, val accuracy: Float)

class CheckOutViewModel(private val idCalendar: Int, private val CheckOutRepository: CheckOutRepository) : ViewModel() {

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


    fun checkOut() {
        _loading.value = true
        _askLocation.call()
    }

    fun permisionsNotGranted() {
        _askPermisions.call()
        loadingVisibleGone()
    }

    fun location(observations: String, latitute: Double, longitude: Double, accuracy: Float) {

        val location = CRMLocation(latitute, longitude, accuracy)

        queryTemporal(idCalendar, observations, location.latitute, location.longitude, location.accuracy)

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


    private fun queryTemporal(idCalendar: Int, observations: String, latitute: Double, longitude: Double, accuracy: Float) {

        CheckOutRepository.insertCheckOut(idCalendar, observations, latitute, longitude, accuracy, object : CheckOutRepository.CheckOutCallback  {
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
