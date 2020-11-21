package es.crmone.app.common

fun String.capitalizeFirstLetter(): String =  this.substring(0, 1).toUpperCase() + this.substring(1)
fun String.hasImageExtension(): Boolean = this.contains("jpg", true) || this.contains("png", true)