package com.belyakov.loginform.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toDateString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
    return simpleDateFormat.format(this.time)
}

fun Long.toDateString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
    return simpleDateFormat.format(this)
}