package com.example.githubclient.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSimpleUser(
    val id: Int,
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
)
