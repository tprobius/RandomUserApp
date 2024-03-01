package com.tprobius.randomuserapp.domain.repository

import com.tprobius.randomuserapp.domain.model.RandomUser

interface RandomUserApiRepository {

    suspend fun getRandomUsersList(): List<RandomUser>
}