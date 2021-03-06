package es.crmone.app.presentation.calendar

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentCalendarBinding
import es.crmone.app.presentation.client_detail.ClientDetailFragmentDirections
import es.crmone.app.repository.calendar.RemoteCalendarRepository


class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel by viewModels<CalendarViewModel> {
        CalendarVMFactory(RemoteCalendarRepository())
    }

    private val listenerCheckOut = { calendar: CalendarOne ->
        viewModel.seleccionar(calendar)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCalendarBinding.bind(view)


        with(binding.rvCalendar) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


        with(viewModel) {
            calendarLD.observe(viewLifecycleOwner) {
                binding.rvCalendar.adapter = CalendarAdapter(it, listenerCheckOut)
            }
            goToCheckOut.observe(viewLifecycleOwner) { idCalendar ->
                findNavController().navigate(
                    CalendarFragmentDirections.actionCalendarToCheckout(idCalendar),
                    fragmentAnimation().build()
                )

            }
            loading.observe(viewLifecycleOwner) { loading ->
                binding.loading.isVisible = loading
            }
        }


        binding.myToolbar.setNavigationOnClickListener {
            val mainFragment: MainFragment? = (parentFragment?.parentFragment as? MainFragment)
            mainFragment?.also {
                it.openDrawer()
            }
        }



    }


    override fun onPause() {
        super.onPause()

        viewModel.loadCalendar()
    }





}