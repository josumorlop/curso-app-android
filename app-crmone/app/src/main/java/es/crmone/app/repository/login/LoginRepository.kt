package es.crmone.app.repository.login

interface LoginRepository {
    interface LoginCallback {
        fun onSuccess(success: Boolean, user: User?=null)
        fun onError()
    }
    fun login(email: String, password: String, callback: LoginCallback)
}