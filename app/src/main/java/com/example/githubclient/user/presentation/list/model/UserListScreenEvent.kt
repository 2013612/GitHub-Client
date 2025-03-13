package com.example.githubclient.user.presentation.list.model

sealed interface UserListScreenEvent {
    data class OnUserClicked(
        val user: String,
    ) : UserListScreenEvent

    data object OnLoadMore : UserListScreenEvent
}
