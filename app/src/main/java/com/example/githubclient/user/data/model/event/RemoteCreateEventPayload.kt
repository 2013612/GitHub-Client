package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCreateEventPayload(
    val ref: String?,
    @SerialName("ref_type")
    val refType: RemoteGitRefType,
) : RemoteUserEventPayload()
