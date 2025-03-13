package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.RemoteUserProfile
import com.example.githubclient.user.domain.model.UserProfile

fun RemoteUserProfile.toUserProfile() =
    UserProfile(
        id = id,
        userName = login,
        name = name,
        avatarUrl = avatarUrl,
        followers = followers,
        following = following,
        company = company,
        location = location,
        email = email,
        blog = blog,
        twitterUsername = twitterUsername,
    )
