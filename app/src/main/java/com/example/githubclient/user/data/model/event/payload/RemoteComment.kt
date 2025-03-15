package com.example.githubclient.user.data.model.event.payload

import com.example.githubclient.user.data.model.event.RemoteActor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteComment(
    @SerialName("html_url")
    val htmlUrl: String,
    val id: Long,
    val user: RemoteActor,
)
