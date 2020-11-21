package es.crmone.app.presentation.client

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.common.BaseFragment
import es.crmone.app.R
import es.crmone.app.databinding.FragmentClientBinding
import es.crmone.app.presentation.client_detail.ClientDetailFragmentDirections
import es.crmone.app.repository.clientes.RemoteClientsRepository


class ClientFragment : BaseFragment<FragmentClientBinding>(R.layout.fragment_client), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val viewModel by viewModels<ClientsViewModel> { ClientVMFactory() }

    private val listenerClient =  { client: Client ->
        viewModel.seleccionar(client)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentClientBinding.bind(view)

        binding.etBuscar.setOnQueryTextListener(this)

        with(binding.rvClients) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        with(viewModel) {
            clientsLD.observe(viewLifecycleOwner) { listaClientes ->
                binding.rvClients.adapter = ClientesAdapter(listaClientes, listenerClient)
            }
            goToClientDetailLD.observe(viewLifecycleOwner) { idClient ->
                findNavController().navigate(ClientFragmentDirections.actionToClientDetail(idClient))
            }
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
