package com.example.githubclient.user.data.model.event.payload

import com.example.githubclient.user.data.model.event.RemoteActor
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMemberEventPayload(
    val member: RemoteActor,
) : RemoteUserEventPayload
