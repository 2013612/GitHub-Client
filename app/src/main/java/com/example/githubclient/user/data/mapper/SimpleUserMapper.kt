package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.RemoteSimpleUser
import com.example.githubclient.user.domain.model.SimpleUser

fun RemoteSimpleUser.toSimpleUser(): SimpleUser =
    SimpleUser(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
    )
