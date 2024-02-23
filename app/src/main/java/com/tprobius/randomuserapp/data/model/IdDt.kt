package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.IdDmn

data class IdDt(
    @SerializedName("name")
    val name: String?,
    @SerializedName("value")
    val value: String?
) {

    fun toIdDmn() = IdDmn(name = name, value = value)
}