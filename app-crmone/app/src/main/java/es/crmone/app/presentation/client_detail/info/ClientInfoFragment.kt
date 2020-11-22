package es.crmone.app.presentation.client_detail.info

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentClientInfoBinding
import es.crmone.app.presentation.client_detail.reports.ReportAdapter
import es.crmone.app.presentation.client_detail.reports.ReportsFragment
import es.crmone.app.presentation.login.LoginFragmentDirections


class ClientInfoFragment : BaseFragment<FragmentClientInfoBinding>(R.layout.fragment_client_info) {
    companion object {
        private const val idClient = "idClient"
        fun create(idClient: Int): ClientInfoFragment {
            val bundle = Bundle()
            bundle.putInt(ClientInfoFragment.idClient, idClient)
            val fragment = ClientInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val idClient by lazy {
        arguments?.getInt(ClientInfoFragment.idClient)?:throw Exception("idClient not found")
    }

    private val viewModel by viewModels<ClientInfoViewModel>() {
        ClientInfoVMFactory(idClient)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentClientInfoBinding.bind(view)



        viewModel.clientinfoLD.observe(viewLifecycleOwner) {
            binding.tvCif.text = it.cif

        }




    }
}
