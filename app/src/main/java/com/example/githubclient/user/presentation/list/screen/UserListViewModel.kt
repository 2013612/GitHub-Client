package com.example.githubclient.user.presentation.list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.common.domain.model.onError
import com.example.githubclient.common.domain.model.onSuccess
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.presentation.list.model.UserListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userRepository: IUserRepository,
) : ViewModel() {
    private val _screenStateFlow = MutableStateFlow(UserListScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()

    private var since = 0

    private fun loadUsers(since: Int) {
        viewModelScope.launch {
            userRepository
                .fetchUserList(since = since)
                .onSuccess { newUsers ->
                    _screenStateFlow.update {
                        it.copy(users = it.users + newUsers)
                    }
                }.onError {
                    println("Error: $it")
                }
        }
    }
}
