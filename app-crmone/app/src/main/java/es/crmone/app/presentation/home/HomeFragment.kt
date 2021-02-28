package es.crmone.app.presentation.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private var activityResult: ActivityResultLauncher<Array<String>>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityResult = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.e("DEBUG", "${it.key} = ${it.value}")
                if (it.key==Manifest.permission.READ_CALL_LOG && it.value) {
                    readCallLog()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        toolbarClickListener()
        binding.callLogBT.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                readCallLog()
            } else {
                activityResult?.launch(arrayOf(android.Manifest.permission.READ_CALL_LOG))
            }
        }
    }

    private fun readCallLog() {
//        val query = arrayOf(
//            CallLog.Calls.NUMBER,
//            CallLog.Calls.TYPE,
//            CallLog.Calls.CACHED_NUMBER_LABEL,
//            CallLog.Calls.CACHED_NUMBER_TYPE,
//            CallLog.Calls.DURATION
//        )
        val callHistory = ArrayList<CrmCall>()
        context?.contentResolver?.query(CallLog.Calls.CONTENT_URI, null, null, null, null)?.apply {
            moveToFirst()
            while (!isAfterLast) {
                val date = getString(getColumnIndex(CallLog.Calls.DATE))
                val name = getString(getColumnIndex(CallLog.Calls.NUMBER))
                val type = getString(getColumnIndex(CallLog.Calls.TYPE)) //INCOMING_TYPE, OUTGOING_TYPE, MISSED_TYPE, VOICEMAIL_TYPE
                val duration = getString(getColumnIndex(CallLog.Calls.DURATION))
                val presentation = getString(getColumnIndex(CallLog.Calls.NUMBER_PRESENTATION))
                val PHONE_ACCOUNT_ID = getString(getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID))
                val PHONE_ACCOUNT_COMPONENT_NAME = getString(getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_COMPONENT_NAME))
                val VIA_NUMBER = getString(getColumnIndex(CallLog.Calls.VIA_NUMBER))
//                val call = CrmCall()
//                callHistory.add(call)
                moveToNext()
            }
            close()
        }
    }
    private fun toolbarClickListener() {
        binding.myToolbar.setNavigationOnClickListener {
            val mainFragment: MainFragment? = (parentFragment?.parentFragment as? MainFragment)
            mainFragment?.also {
                it.openDrawer()
            }
        }
    }

}