package com.example.githubclient.user.presentation.detail.model

import com.example.githubclient.user.domain.model.UserEvent
import com.example.githubclient.user.domain.model.UserProfile

data class UserDetailScreenState(
    val profile: UserProfile? = null,
    val events: List<UserEvent> = emptyList(),
)
