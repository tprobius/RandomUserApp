package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.TimezoneDmn

data class TimezoneDt(
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("description")
    val description: String?
) {

    fun toTimezoneDmn() = TimezoneDmn(offset = offset, description = description)
}