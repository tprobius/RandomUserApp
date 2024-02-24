package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class NameDt(
    @SerializedName("title")
    val title: String?,
    @SerializedName("first")
    val first: String?,
    @SerializedName("last")
    val last: String?
)