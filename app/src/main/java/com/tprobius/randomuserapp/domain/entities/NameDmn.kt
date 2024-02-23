package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameDmn(
    val title: String?,
    val first: String?,
    val last: String?
): Parcelable