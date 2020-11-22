package es.crmone.app.presentation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.crmone.app.di.Injections

internal class ReportVMFactory(private val clientID: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ReportViewModel(
        clientID, Injections.reportRepository, Injections.sessionRepository
    ) as T
}
