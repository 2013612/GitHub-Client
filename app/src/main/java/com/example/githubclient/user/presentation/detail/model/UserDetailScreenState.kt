package com.example.githubclient.user.presentation.detail.model

import com.example.githubclient.user.domain.model.UserProfile

data class UserDetailScreenState(
    val profile: UserProfile? = null,
)
