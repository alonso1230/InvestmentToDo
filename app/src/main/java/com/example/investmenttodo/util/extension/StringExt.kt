package com.example.investmenttodo.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): String = SimpleDateFormat(pattern).format(Date(this.toLong()))