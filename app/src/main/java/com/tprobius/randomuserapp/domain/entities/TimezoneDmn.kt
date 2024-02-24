package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimezoneDmn(
    val offset: String?,
    val description: String?
) : Parcelable