package com.example.githubclient.user.presentation.list.model

import com.example.githubclient.user.domain.model.SimpleUser

data class UserListScreenState(
    val users: List<SimpleUser> = emptyList(),
    val listState: ListState = ListState.IDLE,
)

enum class ListState {
    IDLE,
    LOADING,
    PAGINATING,
    PAGINATION_EXHAUST,
}
