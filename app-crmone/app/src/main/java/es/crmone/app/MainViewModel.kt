package es.crmone.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.crmone.app.common.SingleLiveEvent

import es.crmone.app.repository.login.User
import es.crmone.app.repository.session.SessionRepository

class MainViewModel (private val sessionRepository: SessionRepository) : ViewModel() {

    private val _userSession = SingleLiveEvent<User>()
    val userSessionLD: LiveData<User> = _userSession


    init {
        loadUserSession()
    }
    private fun loadUserSession() {
        val user: User? = sessionRepository.getUser()
        _userSession.value = user
    }









}