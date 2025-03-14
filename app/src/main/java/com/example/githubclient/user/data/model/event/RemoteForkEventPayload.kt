package com.example.githubclient.user.data.model.event

data class RemoteForkEventPayload(
    val forkee: RemoteRepository,
) : RemoteUserEventPayload()
