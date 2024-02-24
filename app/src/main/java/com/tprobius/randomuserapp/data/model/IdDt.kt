package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class IdDt(
    @SerializedName("name")
    val name: String?,
    @SerializedName("value")
    val value: String?
)