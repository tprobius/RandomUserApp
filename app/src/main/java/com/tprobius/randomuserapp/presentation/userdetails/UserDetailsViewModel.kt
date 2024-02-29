package com.tprobius.randomuserapp.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.randomuserapp.domain.model.RandomUser
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val router: UserDetailsRouter
) : ViewModel() {

    private var _state: MutableLiveData<UserDetailsState> = MutableLiveData()
    val state: LiveData<UserDetailsState> = _state

    init {
        _state.value = UserDetailsState.Initial
    }

    fun getUserDetails(user: RandomUser?) {
        viewModelScope.launch {
            _state.value = UserDetailsState.Loading
            if (user != null) {
                _state.value = UserDetailsState.Success
            } else {
                _state.value = UserDetailsState.Error
            }
        }
    }

    fun getUsersList() {
        router.openUsersList()
    }
}