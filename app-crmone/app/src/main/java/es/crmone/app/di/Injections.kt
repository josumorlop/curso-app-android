package es.crmone.app.di

import es.crmone.app.repository.calendar.RemoteCalendarRepository
import es.crmone.app.repository.checkout.RemoteCheckOutRepository
import es.crmone.app.repository.client_create.RemoteClientCreateRepository
import es.crmone.app.repository.client_info.RemoteClientInfoRepository
import es.crmone.app.repository.clientes.RemoteClientsRepository
import es.crmone.app.repository.login.RemoteLoginRepository
import es.crmone.app.repository.map.RemoteCRMMapRepository
import es.crmone.app.repository.report.RemoteReportRepository
import es.crmone.app.repository.session.SessionRepositoryImp

object Injections {
    val loginRepository by lazy { RemoteLoginRepository() }
    val calendarRepository by lazy { RemoteCalendarRepository() }
    val clientRepository by lazy { RemoteClientsRepository() }
    val clientInfoRepository by lazy { RemoteClientInfoRepository() }
    val sessionRepository by lazy { SessionRepositoryImp() }
    val reportRepository by lazy { RemoteReportRepository() }
    val checkOutRepository by lazy { RemoteCheckOutRepository() }
    val clientCreateRepository by lazy { RemoteClientCreateRepository() }
    val crmMapRepository by lazy { RemoteCRMMapRepository() }
}