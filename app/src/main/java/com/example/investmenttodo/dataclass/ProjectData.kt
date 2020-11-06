package com.example.investmenttodo.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectData(
        var projects: MutableList<Project> = ArrayList(),
) : Parcelable