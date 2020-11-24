package es.crmone.app.presentation.client

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.MainFragment
import es.crmone.app.common.BaseFragment
import es.crmone.app.R
import es.crmone.app.databinding.FragmentClientBinding
import es.crmone.app.presentation.client_detail.ClientDetailFragmentDirections
import es.crmone.app.repository.clientes.RemoteClientsRepository


class ClientFragment : BaseFragment<FragmentClientBinding>(R.layout.fragment_client), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private val viewModel by viewModels<ClientsViewModel> { ClientVMFactory() }

    private val listenerClient =  { client: Client ->
        viewModel.seleccionar(client)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentClientBinding.bind(view)

        setupView()
        binding.btNuevoCliente.setOnClickListener {
            Toast.makeText(requireContext(), "No disponible en la BETA", Toast.LENGTH_SHORT).show()
        }

        with(viewModel) {
            clientsLD.observe(viewLifecycleOwner) { listaClientes ->
                binding.rvClients.adapter = ClientesAdapter(listaClientes, listenerClient)
            }
            goToClientDetailLD.observe(viewLifecycleOwner) { idClient ->
                findNavController().navigate(
                    ClientFragmentDirections.actionToClientDetail(idClient),
                    fragmentAnimation().build()
                )
            }
            loading.observe(viewLifecycleOwner) { loading ->
                binding.loading.isVisible = loading
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadClients()
    }
    private fun setupView() {
        binding.etBuscar.setOnCloseListener(this)
        binding.etBuscar.setOnQueryTextListener(this)

        binding.myToolbar.setNavigationOnClickListener {
            val mainFragment: MainFragment? = (parentFragment?.parentFragment as? MainFragment)
            mainFragment?.also {
                it.openDrawer()
            }
        }

        with(binding.rvClients) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
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

    //No entra aquí y no se por que xD: REVISAR
    override fun onClose(): Boolean {
        viewModel.cleanSearch()
        return true
    }

}
