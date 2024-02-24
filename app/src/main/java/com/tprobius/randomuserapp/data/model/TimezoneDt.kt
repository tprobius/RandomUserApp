package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class TimezoneDt(
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("description")
    val description: String?
)