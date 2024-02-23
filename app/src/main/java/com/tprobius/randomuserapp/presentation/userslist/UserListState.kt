package com.tprobius.randomuserapp.presentation.userslist

import com.tprobius.randomuserapp.domain.entities.RandomUser

sealed interface UserListState {

    data object Initial : UserListState
    data object Loading : UserListState
    data class Success(val usersList: List<RandomUser>) : UserListState
    data object Error : UserListState
}