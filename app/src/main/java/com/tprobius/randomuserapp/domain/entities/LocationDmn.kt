package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDmn(
    val streetDmn: StreetDmn?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: CoordinatesDmn?,
    val timezoneDmn: TimezoneDmn?
) : Parcelable