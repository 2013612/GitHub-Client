package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssue(
    val id: Long,
    val number: Int,
    val title: String,
    val user: RemoteActor,
    @SerialName("html_url")
    val htmlUrl: String,
)
