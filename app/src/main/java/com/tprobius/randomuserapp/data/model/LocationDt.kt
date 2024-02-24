package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class LocationDt(
    @SerializedName("street")
    val streetDt: StreetDt?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("coordinates")
    val coordinates: CoordinatesDt?,
    @SerializedName("timezone")
    val timezoneDt: TimezoneDt?
)