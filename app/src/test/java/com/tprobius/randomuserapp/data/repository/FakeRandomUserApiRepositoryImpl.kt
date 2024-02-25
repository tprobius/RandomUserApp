package com.tprobius.randomuserapp.data.repository

import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository

class FakeRandomUserApiRepositoryImpl : RandomUserApiRepository {
    override suspend fun getRandomUsersList(): List<RandomUser> {
        return listOf(
            RandomUser(
                gender = "Female",
                first = "Sansa",
                last = "Stark",
                city = "Winterfell",
                state = " the North"
            )
        )
    }
}