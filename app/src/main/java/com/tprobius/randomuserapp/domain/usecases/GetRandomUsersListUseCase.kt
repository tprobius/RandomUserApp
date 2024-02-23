package com.tprobius.randomuserapp.domain.usecases

import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.repository.RandomUserApiRepository

class GetRandomUsersListUseCase(
    private val apiRepository: RandomUserApiRepository
) {

    suspend operator fun invoke(): List<RandomUser> {
        return apiRepository.getRandomUsersList()
    }
}