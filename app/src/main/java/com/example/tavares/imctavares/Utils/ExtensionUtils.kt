package com.example.tavares.imctavares.Utils

import java.text.DecimalFormat

fun Float.formatToString() : String{
    val dec = DecimalFormat("0.00")
    return dec.format(this).replace(",",".")
}
