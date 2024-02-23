package com.tprobius.randomuserapp.domain.repository

import com.tprobius.randomuserapp.domain.entities.RandomUser

interface RandomUserApiRepository {
    suspend fun getRandomUsersList(): List<RandomUser>
}