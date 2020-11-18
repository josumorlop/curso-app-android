package es.crmone.app.presentation.home

import android.os.Bundle
import android.view.View
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentOneBinding

class Fragment1 : BaseFragment<FragmentOneBinding>(R.layout.fragment_one) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOneBinding.bind(view)
    }
    fun actualizaaaar() {

    }
}