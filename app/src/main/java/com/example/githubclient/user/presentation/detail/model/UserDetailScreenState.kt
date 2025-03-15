package com.example.githubclient.user.presentation.detail.model

import com.example.githubclient.common.presentation.model.PaginationState
import com.example.githubclient.user.domain.model.UserProfile
import com.example.githubclient.user.domain.model.event.UserEvent

data class UserDetailScreenState(
    val profile: UserProfile? = null,
    val events: List<UserEvent> = emptyList(),
    val paginationState: PaginationState = PaginationState.IDLE,
)
