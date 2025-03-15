package com.example.githubclient.user.data.model.event.payload

import com.example.githubclient.user.data.model.event.RemoteRepository
import kotlinx.serialization.Serializable

@Serializable
data class RemoteForkEventPayload(
    val forkee: RemoteRepository,
) : RemoteUserEventPayload
