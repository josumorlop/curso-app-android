package es.crmone.app.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import es.crmone.app.repository.calendar.CalendarRepository


internal class CalendarVMFactory(private val calendarRepository: CalendarRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CalendarViewModel(
        calendarRepository
    ) as T
}
