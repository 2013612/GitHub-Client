package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCreateEventPayload(
    val ref: String?,
    val refType: RemoteGitRefType,
) : RemoteUserEventPayload()
