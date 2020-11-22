package es.crmone.app.presentation.report

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentReportBinding


class ReportFragment : BaseFragment<FragmentReportBinding>(R.layout.fragment_report) {
    private val REQUEST_CODE_LOCATION = 1
    private val viewModel by viewModels<ReportViewModel> {
        ReportVMFactory()
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReportBinding.bind(view)
        setupBackButton(binding.myToolbar)
        binding.btCheckIn.setOnClickListener {
            viewModel.checkIn()
        }
        with(viewModel) {
            askLocation.observe(viewLifecycleOwner) {
                askForLocation()
            }
            askPermisions.observe(viewLifecycleOwner) {
                askPermisions()
            }
            closeReport.observe(viewLifecycleOwner) {
                findNavController().navigateUp()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                            && (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                            && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    askForLocation()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    viewModel.userDenyPermissions()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }

    }

    private fun askPermisions() {
        val permisos = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        requestPermissions(permisos, REQUEST_CODE_LOCATION)
    }

    private fun askForLocation() {
        val permissionCheck = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            val cts = CancellationTokenSource()
            if (!::fusedLocationClient.isInitialized) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cts.token)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful && task.result!=null) {
                            viewModel.location(
                                binding.tvObservations.text.toString(),
                                task.result.latitude,
                                task.result.longitude,
                                task.result.accuracy)
                        }
                }
            } else { // if isInitialized == true
                fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cts.token)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful && task.result!=null) {
                            viewModel.location(
                                binding.tvObservations.text.toString(),
                                task.result.latitude,
                                task.result.longitude,
                                task.result.accuracy)
                        }
                    }
            }
        } else {
            viewModel.permisionsNotGranted()
        }
    }

}