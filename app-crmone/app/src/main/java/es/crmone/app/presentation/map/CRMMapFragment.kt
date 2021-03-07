package es.crmone.app.presentation.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentMapBinding


class CRMMapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val viewModel by viewModels<CRMMapViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMapBinding.bind(view)

        binding.myToolbar.setNavigationOnClickListener {
            val mainFragment: MainFragment? = (parentFragment?.parentFragment as? MainFragment)
            mainFragment?.also {
                it.openDrawer()
            }
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true

        viewModel.getPositons().observe(viewLifecycleOwner) { operation ->
            when(operation) {
                PositionsOp.Error -> {

                }
                PositionsOp.Loading -> {

                }
                is PositionsOp.Success -> {
                    val madrid = LatLng(40.4178446,-3.7153383)
                    val cameraUpdate = CameraUpdateFactory.newCameraPosition(
                        CameraPosition.builder().
                        target(madrid).
                        zoom(13f).
                        build()
                    )
                    mMap.moveCamera(cameraUpdate)
                    operation.locations.forEach {
                        mMap.addMarker(MarkerOptions()
                            .position(it.latLng)
                            .title(it.title))
                    }
                }
            }
        }
    }
}
