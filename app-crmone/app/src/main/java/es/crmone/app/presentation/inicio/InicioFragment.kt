package es.crmone.app.presentation.inicio

import android.os.Bundle
import android.view.View
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentInicioBinding

class InicioFragment : BaseFragment<FragmentInicioBinding>(R.layout.fragment_inicio) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInicioBinding.bind(view)

    }
}