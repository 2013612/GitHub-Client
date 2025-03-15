package com.example.githubclient.user.presentation.list.model

import com.example.githubclient.common.presentation.model.PaginationState
import com.example.githubclient.user.domain.model.SimpleUser

data class UserListScreenState(
    val users: List<SimpleUser> = emptyList(),
    val paginationState: PaginationState = PaginationState.IDLE,
)
