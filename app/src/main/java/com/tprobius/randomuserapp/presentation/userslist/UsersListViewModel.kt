package com.tprobius.randomuserapp.presentation.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.randomuserapp.domain.usecases.GetRandomUsersListUseCase
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val getRandomUsersListUseCase: GetRandomUsersListUseCase
) : ViewModel() {
    private var _state: MutableLiveData<UserListState> = MutableLiveData()
    val state: LiveData<UserListState> = _state

    init {
        _state.value = UserListState.Initial
    }

    fun getRecipeList(result: Int) {
        viewModelScope.launch {
            _state.value = UserListState.Loading

            try {
                val res = getRandomUsersListUseCase(result)
                res.let {
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
}