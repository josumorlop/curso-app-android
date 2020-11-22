package es.crmone.app.presentation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections

internal class ReportVMFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ReportViewModel(
        Injections.sessionRepository
    ) as T
}
