package com.tprobius.randomuserapp.domain.usecases

import com.tprobius.randomuserapp.MainActivity
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository
import com.tprobius.randomuserapp.domain.repository.RandomUserDatabaseRepository

class GetRandomUsersListUseCase(
    private val apiRepository: RandomUserApiRepository,
    private val databaseRepository: RandomUserDatabaseRepository
) {

    suspend operator fun invoke(): List<RandomUser> {
        return if (MainActivity.isFirst.last()) {
            val usersList = apiRepository.getRandomUsersList()
            if (usersList.isNotEmpty()) {
                databaseRepository.insertUsersList(usersList)
            }
            usersList
        } else {
            databaseRepository.getUsersList()
        }
    }
}