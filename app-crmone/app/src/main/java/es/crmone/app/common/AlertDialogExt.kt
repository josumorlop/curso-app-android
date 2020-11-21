package es.crmone.app.common

import androidx.appcompat.app.AlertDialog

fun AlertDialog.Builder.addPositiveButton(text: String = "Aceptar", handleClick: (which: Int) -> Unit = {}) {
    this.setPositiveButton(text, { dialogInterface, which-> handleClick(which) })
}

fun AlertDialog.Builder.addNegativeButton(text: String = "Cancelar", handleClick: (which: Int) -> Unit = {}) {
    this.setNegativeButton(text, { dialogInterface, which-> handleClick(which) })
}
fun AlertDialog.Builder.addNegativeButtonWithoutListener(text: String = "Cancelar") {
    this.setNegativeButton(text, null)
}