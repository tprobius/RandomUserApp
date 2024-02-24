package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IdDmn(
    val name: String?,
    val value: String?
) : Parcelable