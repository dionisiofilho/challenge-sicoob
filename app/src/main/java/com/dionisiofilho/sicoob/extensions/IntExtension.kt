package com.dionisiofilho.sicoob.extensions

fun Int.formatHoursAndMinutes(): String {
    var minutes = Integer.toString(this % 60)
    minutes = if (minutes.length == 1) "0$minutes" else minutes
    return (this / 60).toString() + "h:" + minutes + "m"
}