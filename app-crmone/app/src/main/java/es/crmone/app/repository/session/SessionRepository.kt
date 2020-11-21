package es.crmone.app.repository.session

import es.crmone.app.common.CRMOnePrefs
import es.crmone.app.repository.login.User

interface SessionRepository {
    fun isUserLogged(): Boolean
    fun getUser(): User?
    fun save(user: User)
}

class SessionRepositoryImp(): SessionRepository {
    override fun isUserLogged() = CRMOnePrefs.getUser()!=null
    override fun getUser() = CRMOnePrefs.getUser()
    override fun save(user: User) {
        CRMOnePrefs.save(user)
    }
}
