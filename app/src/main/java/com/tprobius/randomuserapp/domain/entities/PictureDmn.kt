package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureDmn(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
): Parcelable