package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class CoordinatesDt(
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?
)