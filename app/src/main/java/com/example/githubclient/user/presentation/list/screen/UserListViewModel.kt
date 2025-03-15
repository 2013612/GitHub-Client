package com.example.githubclient.user.presentation.list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.common.domain.model.onError
import com.example.githubclient.common.domain.model.onSuccess
import com.example.githubclient.common.presentation.model.PaginationState
import com.example.githubclient.user.domain.IUserRepository
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
            is UserListScreenEvent.OnRefresh -> loadUsers(since = 0)
            is UserListScreenEvent.OnUserClicked -> {}
        }
    }

    private fun loadUsers(since: Int) {
        val paginationState = screenStateFlow.value.paginationState

        when (paginationState) {
            PaginationState.LOADING, PaginationState.PAGINATING -> return
            PaginationState.PAGINATION_EXHAUST -> if (since != 0) return
            PaginationState.IDLE -> {}
        }

        _screenStateFlow.update {
            it.copy(
                paginationState =
                    if (since == 0) {
                        PaginationState.LOADING
                    } else {
                        PaginationState.PAGINATING
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
                            paginationState =
                                if (newUsers.size < PAGE_SIZE) {
                                    PaginationState.PAGINATION_EXHAUST
                                } else {
                                    PaginationState.IDLE
                                },
                        )
                    }
                }.onError {
                    println("Error: $it")
                    _screenStateFlow.update {
                        it.copy(
                            paginationState = PaginationState.IDLE,
                        )
                    }
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 100
    }
}
