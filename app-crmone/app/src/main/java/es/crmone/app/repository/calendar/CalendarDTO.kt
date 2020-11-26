package es.crmone.app.repository.calendar

import es.crmone.app.presentation.calendar.CalClient
import es.crmone.app.presentation.calendar.CalUser


class CalendarDTO (
    val id: Int,
    val cliente: CalClient,
    val usuarioRegistro: CalUser,
    val checkin: String?,
    val fecha: String,
    val hora: String,
    val horaFin: String?,
    val comentarios: String?
)