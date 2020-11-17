package es.crmone.app.presentation.client

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.common.BaseFragment
import es.crmone.app.R
import es.crmone.app.databinding.FragmentClientBinding
import es.crmone.app.repository.clientes.RemoteClientsRepository


class ClientFragment : BaseFragment<FragmentClientBinding>(R.layout.fragment_client), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val viewModel by viewModels<ClientsViewModel> {
        ClientVMFactory(RemoteClientsRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentClientBinding.bind(view)
        with(binding.rvClients) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewModel.clientsLD.observe(viewLifecycleOwner) {
            binding.rvClients.adapter = ClientesAdapter(it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query != null) {

            viewModel.loadClientsQuery(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}