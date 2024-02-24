package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class StreetDt(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?
)