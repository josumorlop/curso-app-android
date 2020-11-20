package es.crmone.app.presentation.report

import android.os.Bundle
import android.view.View
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentReportBinding

class ReportFragment : BaseFragment<FragmentReportBinding>(R.layout.fragment_report) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReportBinding.bind(view)

    }


}