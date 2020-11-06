package com.example.investmenttodo.dataclass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
        @PrimaryKey
        var name: String,
        var password: String,
        var backgroundColor: Int,
) : Parcelable