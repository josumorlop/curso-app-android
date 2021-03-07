package es.crmone.app.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import es.crmone.app.repository.map.CRMMapRepository
import es.crmone.app.repository.session.SessionRepository
import kotlinx.coroutines.flow.flow

class CrmLocation(val title: String, val latLng: LatLng)
sealed class PositionsOp {
    object Error: PositionsOp()
    object Loading: PositionsOp()
    class Success(val locations: List<CrmLocation>): PositionsOp()
}


class CRMMapViewModel (private val CRMMapRepository: CRMMapRepository, private  val sessionRepository: SessionRepository): ViewModel() {

    fun getPositons(): LiveData<PositionsOp> {
        return flow {
            //inicio llamada
//            emit(PositionsOp.Loading)
            //fin llamada

            //Respuesta del servicio que te devuelte las posiciones
            val locations: List<CrmLocation> = listOf(
                CrmLocation("Casa Ricardo", LatLng(40.4297934,-3.7123437)),
                CrmLocation("Go fit", LatLng(40.4410008,-3.7108467)),
            )
            val response = PositionsOp.Success(locations)
            emit(response)
        }.asLiveData(viewModelScope.coroutineContext)
    }
}