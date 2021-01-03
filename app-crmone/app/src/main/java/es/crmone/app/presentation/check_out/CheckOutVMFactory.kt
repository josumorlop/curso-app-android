package es.crmone.app.presentation.check_out

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections


internal class CheckOutVMFactory(private val idCalendar: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CheckOutViewModel(
        idCalendar, Injections.checkOutRepository, Injections.sessionRepository
    ) as T
}
