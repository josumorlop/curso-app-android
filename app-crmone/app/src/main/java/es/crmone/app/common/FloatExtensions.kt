package es.crmone.app.common

fun Float.formatImporte() : String = String.format("%.2f", this)
fun Float.formatImporteEuros() : String = "${String.format("%.2f", this)}€"