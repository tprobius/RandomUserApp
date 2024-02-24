package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreetDmn(
    val number: Int?,
    val name: String?
) : Parcelable