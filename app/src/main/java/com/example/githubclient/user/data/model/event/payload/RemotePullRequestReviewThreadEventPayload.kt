package com.example.githubclient.user.data.model.event.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePullRequestReviewThreadEventPayload(
    val action: RemotePullRequestReviewThreadAction,
) : RemoteUserEventPayload

enum class RemotePullRequestReviewThreadAction {
    @SerialName("resolved")
    Resolved,

    @SerialName("unresolved")
    Unresolved,
}
