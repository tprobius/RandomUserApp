package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.PictureDmn

data class PictureDt(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
) {

    fun toPictureDmn() = PictureDmn(large = large, medium = medium, thumbnail = thumbnail)
}