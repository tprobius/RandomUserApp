package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.LocationDmn

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
    @SerializedName("timezone")
    val timezoneDt: TimezoneDt?
) {

    fun toLocationDmn() = LocationDmn(
        streetDmn = streetDt?.toStreetDmn(),
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        timezoneDmn = timezoneDt?.toTimezoneDmn()
    )
}