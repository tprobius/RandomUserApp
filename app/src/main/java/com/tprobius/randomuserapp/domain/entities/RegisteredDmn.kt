package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisteredDmn(
    val date: String?,
    val age: Int?
): Parcelable