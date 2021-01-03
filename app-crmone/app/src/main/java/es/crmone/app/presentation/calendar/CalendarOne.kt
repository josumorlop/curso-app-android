package es.crmone.app.presentation.calendar

class CalClient (
    val cif: String,
    val razonSocial: String
)

class CalUser (
    val nombre: String,
    val apellido1: String
)

class CalendarOne (
    val id: Int,
    val cliente: CalClient,
    val usuarioRegistro: CalUser,
    val checkin: String?,
    val checkout: String?,
    val fecha: String,
    val hora: String,
    val horaFin: String?,
    val comentarios: String?,
    val comentarios2: String?,
    val permisoCheckOut: Boolean
)