package es.crmone.app.repository.login

data class UserProfile (
    val id: Int,
    val crm: String,
    val name: String,
    val email: String
)

data class User (
    val id: String,
    val profile: UserProfile,
)

