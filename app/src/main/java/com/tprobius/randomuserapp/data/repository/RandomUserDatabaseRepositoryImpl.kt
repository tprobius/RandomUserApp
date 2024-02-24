package com.tprobius.randomuserapp.data.repository

import com.tprobius.randomuserapp.data.database.RandomUserDao
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RandomUserDatabaseRepositoryImpl(
    private val randomUserDao: RandomUserDao,
    private val dispatcher: CoroutineDispatcher
) : RandomUserDatabaseRepository {

    override suspend fun insertUser(user: RandomUser) {
        return withContext(dispatcher) { randomUserDao.insertUser(user) }
    }

    override suspend fun insertUsersList(usersList: List<RandomUser>) {
        return withContext(dispatcher) { randomUserDao.insertUsersList(usersList) }
    }

    override suspend fun getUsersList(): List<RandomUser> {
        return withContext(dispatcher) { randomUserDao.getUsersList() }
    }

    override suspend fun deleteUsersList() {
        return withContext(dispatcher) { randomUserDao.deleteUsersList() }
    }
}