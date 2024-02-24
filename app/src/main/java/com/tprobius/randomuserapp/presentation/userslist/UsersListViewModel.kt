package com.tprobius.randomuserapp.presentation.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.domain.usecases.GetRandomUsersListUseCase
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val getRandomUsersListUseCase: GetRandomUsersListUseCase,
    private val router: UsersListRouter
) : ViewModel() {

    private var _state: MutableLiveData<UserListState> = MutableLiveData()
    val state: LiveData<UserListState> = _state

    init {
        _state.value = UserListState.Initial
    }

    fun getUsersList() {
        viewModelScope.launch {
            _state.value = UserListState.Loading

            try {
                getRandomUsersListUseCase().let {
                    if (it.isEmpty()) {
                        _state.postValue(UserListState.Error)
                    } else {
                        _state.postValue(UserListState.Success(it))
                    }
                }
            } catch (e: Exception) {
                _state.postValue(UserListState.Error)
            }
        }
    }

    fun getUserDetails(user: RandomUser) {
        router.openUserDetails(user)
    }
}