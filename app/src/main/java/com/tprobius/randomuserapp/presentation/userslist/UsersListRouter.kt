package com.tprobius.randomuserapp.presentation.userslist

import com.tprobius.randomuserapp.domain.entities.RandomUser

interface UsersListRouter {

    fun openUserDetails(user: RandomUser)
}