package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DobDmn(
    val date: String?,
    val age: Int?
) : Parcelable