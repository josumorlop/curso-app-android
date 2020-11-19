package es.crmone.app.presentation.home

import android.os.Bundle
import android.view.View
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentTwoBinding

class Fragment2: BaseFragment<FragmentTwoBinding>(R.layout.fragment_two) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTwoBinding.bind(view)
    }
}