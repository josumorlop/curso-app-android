package es.crmone.app.presentation.client_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentClientDetailBinding
import es.crmone.app.repository.calendar.RemoteCalendarRepository


class ClientDetailFragment : BaseFragment<FragmentClientDetailBinding>(R.layout.fragment_client_detail) {

    private val inputArgs: ClientDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<ClientDetailViewModel> {
        ClientDetailVMFactory(inputArgs.idClient, RemoteCalendarRepository())
    }
    private lateinit var adapter: ClientDetailPagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentClientDetailBinding.bind(view)
        adapter = ClientDetailPagesAdapter(this, inputArgs.idClient)
        setupTabViews()

        toolbarClickListener()
    }

    private fun setupTabViews() {
        with(binding) {
            vp.adapter = adapter
            TabLayoutMediator(tabs, vp) { tab, position ->

                when (position) {
                    0 -> {
                        tab.text = "Visitas"
                        tab.setIcon(R.drawable.ic_calendar)

                        val badge = tab.getOrCreateBadge()
                        badge.number = 3

                    }
                    1 -> {
                        tab.text = "Detalles"
                        tab.setIcon(R.drawable.ic_client)
                    }
                }

            }.attach()

        }
        with(viewModel) {
            pendingsLD.observe(viewLifecycleOwner) {
                //actualizar tabs
            }
        }
    }


    private fun toolbarClickListener() {
        binding.myToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

}