package es.crmone.app.presentation.client_detail.reports

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentReportsBinding
import es.crmone.app.presentation.client_detail.ClientDetailViewModel
import es.crmone.app.repository.calendar.RemoteCalendarRepository

class ReportsFragment : BaseFragment<FragmentReportsBinding>(R.layout.fragment_reports) {
    companion object {
        private const val idClient = "idClient"
        fun create(idClient: Int): ReportsFragment {
            val bundle = Bundle()
            bundle.putInt(ReportsFragment.idClient, idClient)
            val fragment = ReportsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private val idClient by lazy {
        arguments?.getInt(ReportsFragment.idClient)?:throw Exception("idClient not found")
    }
    private val viewModel by viewModels<ReportsViewModel>() {
        ReportsVMFactory(idClient)
    }
    private lateinit var viewModelPadreClientDetail: ClientDetailViewModel
    lateinit var adapter: ReportAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelPadreClientDetail = ViewModelProvider(requireParentFragment()).get(ClientDetailViewModel::class.java)
        _binding = FragmentReportsBinding.bind(view)
        with(binding) {
            with(rvReports) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        with(viewModel) {
            reportsLD.observe(viewLifecycleOwner) {
                adapter = ReportAdapter(it)
                binding.rvReports.adapter = adapter
            }
            pendingsLD.observe(viewLifecycleOwner) { pendings ->
                viewModelPadreClientDetail.update(pendings)
            }
            loading.observe(viewLifecycleOwner) { loading ->
                binding.loading.isVisible = loading
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCalendar()
    }
}
