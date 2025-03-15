package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteForkEventPayload(
    val forkee: RemoteRepository,
) : RemoteUserEventPayload()
