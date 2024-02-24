package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class PictureDt(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)