package com.example.githubclient.user.presentation.list.model

import com.example.githubclient.user.domain.model.SimpleUser

data class UserListScreenState(
    val users: List<SimpleUser> = emptyList(),
)
