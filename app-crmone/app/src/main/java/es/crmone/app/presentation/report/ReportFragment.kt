package es.crmone.app.presentation.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentHomeBinding
import es.crmone.app.databinding.FragmentReportBinding
import es.crmone.app.presentation.home.HomePagesAdapter

class ReportFragment : BaseFragment<FragmentReportBinding>(R.layout.fragment_report) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReportBinding.bind(view)

    }


}