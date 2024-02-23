package com.tprobius.randomuserapp.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.presentation.userdetails.getUserDetailsScreen
import com.tprobius.randomuserapp.presentation.userslist.UsersListRouter

class UsersListRouterImpl(
    private val router: Router
) : UsersListRouter {
    override fun openUserDetails(user: RandomUser) {
        router.navigateTo(getUserDetailsScreen(user))
    }
}