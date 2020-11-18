package es.crmone.app.presentation.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentCalendarBinding
import es.crmone.app.repository.calendar.RemoteCalendarRepository


class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel by viewModels<CalendarViewModel> {
        CalendarVMFactory(RemoteCalendarRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCalendarBinding.bind(view)


        with(binding.rvCalendar) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewModel.calendarLD.observe(viewLifecycleOwner) {
            binding.rvCalendar.adapter = CalendarAdapter(it)
        }


    }

}