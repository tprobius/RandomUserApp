package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinatesDmn(
    val latitude: String?,
    val longitude: String?
) : Parcelable