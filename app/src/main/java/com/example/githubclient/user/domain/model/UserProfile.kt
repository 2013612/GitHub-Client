package com.example.githubclient.user.domain.model

data class UserProfile(
    val id: Int,
    val userName: String,
    val name: String?,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
    val company: String?,
    val location: String?,
    val email: String?,
    val blog: String?,
    val twitterUsername: String?,
)
