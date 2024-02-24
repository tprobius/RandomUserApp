package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName

data class RegisteredDt(
    @SerializedName("date")
    val date: String?,
    @SerializedName("age")
    val age: Int?
)