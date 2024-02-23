package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.StreetDmn

data class StreetDt(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?
) {

    fun toStreetDmn() = StreetDmn(number = number, name = name)
}