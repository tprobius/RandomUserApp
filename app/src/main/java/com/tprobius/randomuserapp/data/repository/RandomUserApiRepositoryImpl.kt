package com.tprobius.randomuserapp.data.repository

import com.tprobius.randomuserapp.data.api.RandomUserApi
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RandomUserApiRepositoryImpl(
    private val randomUserApi: RandomUserApi,
    private val dispatcher: CoroutineDispatcher
) : RandomUserApiRepository {

    override suspend fun getRandomUsersList(): List<RandomUser> {
        val usersList: MutableList<RandomUser> = mutableListOf()

        withContext(dispatcher) {
            randomUserApi.getRandomUserList().resultDts?.forEach {
                it?.let { result -> usersList.add(result.toRandomUser()) }
            }
        }

        return usersList
    }
}