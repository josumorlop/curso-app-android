package es.crmone.app.models

data class UserProfile (
    val crm: String,
    val name: String,
)
data class User (
    val id: String,
    val profile: UserProfile,
)

