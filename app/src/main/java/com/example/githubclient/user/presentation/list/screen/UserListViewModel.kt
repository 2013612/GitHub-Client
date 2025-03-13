package com.example.githubclient.user.presentation.list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.common.domain.model.onError
import com.example.githubclient.common.domain.model.onSuccess
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.presentation.list.model.ListState
import com.example.githubclient.user.presentation.list.model.UserListScreenEvent
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

    private val lastUserId: Int
        get() =
            screenStateFlow.value.users
                .lastOrNull()
                ?.id ?: 0

    fun onEvent(event: UserListScreenEvent) {
        when (event) {
            is UserListScreenEvent.OnLoadMore -> loadUsers(since = lastUserId)
            else -> {}
        }
    }

    private fun loadUsers(since: Int) {
        val listState = screenStateFlow.value.listState

        when (listState) {
            ListState.LOADING, ListState.PAGINATING -> return
            ListState.PAGINATION_EXHAUST -> if (since != 0) return
            ListState.IDLE -> {}
        }

        _screenStateFlow.update {
            it.copy(
                listState =
                    if (since == 0) {
                        ListState.LOADING
                    } else {
                        ListState.PAGINATING
                    },
            )
        }

        viewModelScope.launch {
            userRepository
                .fetchUserList(since = since, perPage = PAGE_SIZE)
                .onSuccess { newUsers ->
                    _screenStateFlow.update {
                        it.copy(
                            users = it.users + newUsers,
                            listState =
                                if (newUsers.size < PAGE_SIZE) {
                                    ListState.PAGINATION_EXHAUST
                                } else {
                                    ListState.IDLE
                                },
                        )
                    }
                }.onError {
                    println("Error: $it")
                    _screenStateFlow.update {
                        it.copy(
                            listState = ListState.IDLE,
                        )
                    }
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 100
    }
}
