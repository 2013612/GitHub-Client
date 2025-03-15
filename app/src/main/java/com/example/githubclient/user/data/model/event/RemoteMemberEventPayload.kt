package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteMemberEventPayload(
    val member: RemoteActor,
) : RemoteUserEventPayload()
