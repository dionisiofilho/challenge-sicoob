package com.dionisiofilho.sicoob.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getDatePtBr(): String {
    val formato = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    return formato.format(this)
}