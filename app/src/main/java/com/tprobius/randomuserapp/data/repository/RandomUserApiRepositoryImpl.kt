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

    override suspend fun getRandomUsersList(result: Int): List<RandomUser> {
        val usersList: MutableList<RandomUser> = mutableListOf()

        withContext(dispatcher) {
            randomUserApi.getRandomUserList(result).resultDts?.forEach {
                it?.let { it1 -> usersList.add(it1.toRandomUser()) }
            }

        }

        return usersList
    }
}