package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePushEventPayload(
    @SerialName("push_id")
    val pushId: Long,
    val size: Int,
    @SerialName("distinct_size")
    val distinctSize: Int,
    val ref: String,
) : RemoteUserEventPayload()
