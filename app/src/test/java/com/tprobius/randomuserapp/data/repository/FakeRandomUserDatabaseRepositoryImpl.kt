package com.tprobius.randomuserapp.data.repository

import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserDatabaseRepository

/**
 * Fake [RandomUserDatabaseRepository] interface implementation for unit tests
 */
class FakeRandomUserDatabaseRepositoryImpl : RandomUserDatabaseRepository {
    override suspend fun insertUser(user: RandomUser) = Unit

    override suspend fun insertUsersList(usersList: List<RandomUser>) = Unit

    override suspend fun getUsersList(): List<RandomUser> {
        return listOf(
            RandomUser(
                gender = "Male",
                first = "Jon",
                last = "Snow",
                city = "Castle Black",
                state = "The Wall"
            )
        )
    }

    override suspend fun deleteUsersList() = Unit
}