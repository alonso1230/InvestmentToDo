package com.example.investmenttodo.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
        var name: String,
        var description: String?,
        var createDate: String,
        var resolveDate: String?,
        var deadlineDate: String?,
        var members: MutableList<String>,
        var executorMember: String,
        var createMember: String,
        var comments: MutableList<Comment>?,
) : Parcelable