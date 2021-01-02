package es.crmone.app.presentation.check_out

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentCheckOutBinding



class CheckOutFragment : BaseFragment<FragmentCheckOutBinding>(R.layout.fragment_check_out) {

    private val inputArgs: CheckOutFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCheckOutBinding.bind(view)
        setupBackButton(binding.myToolbar)
        //binding.tvIdCalendar.text = inputArgs.idCalendar.toString()

    }



}