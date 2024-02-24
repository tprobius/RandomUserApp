package com.tprobius.randomuserapp.domain.repository

import com.tprobius.randomuserapp.domain.entities.RandomUser

interface RandomUserDatabaseRepository {

    suspend fun insertUser(user: RandomUser)

    suspend fun insertUsersList(usersList: List<RandomUser>)

    suspend fun getUsersList(): List<RandomUser>

    suspend fun deleteUsersList()
}