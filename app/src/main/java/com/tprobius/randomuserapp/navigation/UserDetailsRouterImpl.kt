package com.tprobius.randomuserapp.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.randomuserapp.presentation.userdetails.UserDetailsRouter
import com.tprobius.randomuserapp.presentation.userslist.getUsersListScreen

class UserDetailsRouterImpl(
    private val router: Router
) : UserDetailsRouter {

    override fun openUsersList() {
        router.backTo(getUsersListScreen())
    }
}