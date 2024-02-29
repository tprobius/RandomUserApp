package com.tprobius.randomuserapp.presentation.userdetails

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.tprobius.randomuserapp.domain.model.RandomUser

fun getUserDetailsScreen(user: RandomUser) =
    FragmentScreen { UserDetailsFragment.newInstance(user) }