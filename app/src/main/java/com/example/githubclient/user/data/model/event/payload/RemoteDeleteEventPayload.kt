package com.example.githubclient.user.data.model.event.payload

import com.example.githubclient.user.data.model.event.RemoteGitRefType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteDeleteEventPayload(
    val ref: String,
    @SerialName("ref_type")
    val refType: RemoteGitRefType,
) : RemoteUserEventPayload
