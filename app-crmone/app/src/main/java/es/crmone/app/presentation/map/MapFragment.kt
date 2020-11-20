package es.crmone.app.presentation.map

import android.os.Bundle
import android.view.View

import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentMapBinding


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMapBinding.bind(view)


    }



}


