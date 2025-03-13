package com.example.githubclient.user.presentation.detail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.githubclient.common.domain.model.onError
import com.example.githubclient.common.domain.model.onSuccess
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val userRepository: IUserRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val routeArguments = savedStateHandle.toRoute<NavUserDetailScreen>()

    private val _screenStateFlow = MutableStateFlow(UserDetailScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()

    init {
        fetchUserProfile(routeArguments.userName)
    }

    private fun fetchUserProfile(userName: String) {
        viewModelScope.launch {
            userRepository
                .fetchUserProfile(userName = userName)
                .onSuccess {
                    _screenStateFlow.value = _screenStateFlow.value.copy(profile = it)
                }.onError {
                    println("Error: $it")
                }
        }
    }
}
