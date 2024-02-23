package com.tprobius.randomuserapp.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results")
    val resultDts: List<ResultDt?>?
)