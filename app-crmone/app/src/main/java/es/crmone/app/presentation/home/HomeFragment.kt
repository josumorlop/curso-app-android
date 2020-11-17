package es.crmone.app.presentation.home

import android.os.Bundle
import android.view.View
import es.crmone.app.R
import es.crmone.app.common.BaseFragment

import es.crmone.app.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

    }

}