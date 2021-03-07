package es.crmone.app.repository.map

import com.google.android.gms.maps.model.LatLng


class CRMMapDTO (
    val id: Int?,
    val title: String?,
    val latitute: Double?,
    val longitude: Double?
) {
    fun getLocation(): LatLng? {
        if (latitute!=null && longitude!=null){
            return LatLng(latitute, longitude)
        }
        return null
    }
}


