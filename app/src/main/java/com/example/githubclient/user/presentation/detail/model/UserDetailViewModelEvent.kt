package com.example.githubclient.user.presentation.detail.model

interface UserDetailViewModelEvent {
    data class OnFetchError(
        val isNoInternetError: Boolean,
    ) : UserDetailViewModelEvent
}
