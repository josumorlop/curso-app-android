package es.crmone.app.common

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import es.crmone.app.MyApp
import es.crmone.app.repository.login.User

object CRMOnePrefs {
    private val gson = Gson()
    private val PREFERENCE_NAME = "CrmOnePrefs"
    private const val userKey = "userKey"
    val shared by lazy {
        MyApp.instance.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    }
    fun save(user: User) {
        val userString = gson.toJson(user)
        shared.edit().putString(userKey, userString).apply()
    }
    fun getUser(): User? {
        val userString = shared.getString(userKey, null)
        if (userString!=null) {
            val user: User? = gson.fromJson(userString, User::class.java)
            return user
        } else {
            return null
        }
    }
    fun removeUser() {
        shared.edit().remove(userKey).apply()
    }
}