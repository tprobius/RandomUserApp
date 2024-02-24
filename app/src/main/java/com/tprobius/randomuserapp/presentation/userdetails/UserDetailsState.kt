package com.tprobius.randomuserapp.presentation.userdetails

sealed interface UserDetailsState {

    data object Initial : UserDetailsState
    data object Loading : UserDetailsState
    data object Success : UserDetailsState
    data object Error : UserDetailsState
}