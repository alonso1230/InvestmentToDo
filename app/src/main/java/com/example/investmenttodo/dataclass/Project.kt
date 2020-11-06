package com.example.investmenttodo.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Project(
        var name: String,
        var openCards: MutableList<Card> = ArrayList(),
        var inProgressCards: MutableList<Card> = ArrayList(),
        var resolveCards: MutableList<Card> = ArrayList(),
) : Parcelable