package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.DobDmn

data class DobDt(
    @SerializedName("date")
    val date: String?,
    @SerializedName("age")
    val age: Int?
)