package com.tprobius.randomuserapp.data.api

import com.tprobius.randomuserapp.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api")
    suspend fun getRandomUserList(
        @Query("results") results: Int = BASE_COUNT
    ): ApiResponse

    companion object {
        const val BASE_URL = "https://randomuser.me/"
        const val BASE_COUNT = 150
    }
}