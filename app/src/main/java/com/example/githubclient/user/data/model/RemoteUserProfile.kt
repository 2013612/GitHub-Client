package com.example.githubclient.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserProfile(
    val id: Int,
    val login: String,
    val name: String?,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val company: String?,
    val location: String?,
    val email: String?,
    val blog: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
)
