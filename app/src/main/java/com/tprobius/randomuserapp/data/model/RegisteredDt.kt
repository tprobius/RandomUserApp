package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.RegisteredDmn

data class RegisteredDt(
    @SerializedName("date")
    val date: String?,
    @SerializedName("age")
    val age: Int?
) {

    fun toRegisteredDmn() = RegisteredDmn(date = date, age = age)
}