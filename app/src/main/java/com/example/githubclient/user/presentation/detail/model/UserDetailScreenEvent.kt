package com.example.githubclient.user.presentation.detail.model

sealed interface UserDetailScreenEvent {
    data object OnBackClicked : UserDetailScreenEvent

    data object OnLoadMore : UserDetailScreenEvent

    data class OnTabClicked(
        val index: Int,
    ) : UserDetailScreenEvent
}
