package com.tprobius.randomuserapp.presentation.userslist

import com.tprobius.randomuserapp.domain.model.RandomUser

interface UsersListRouter {

    fun openUserDetails(user: RandomUser)
}