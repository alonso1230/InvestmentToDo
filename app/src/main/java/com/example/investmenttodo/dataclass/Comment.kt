package com.example.investmenttodo.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
        var text: String,
        var date: String,
        var author: String,
) : Parcelable