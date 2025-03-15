package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePullRequestEventPayload(
    val action: RemotePullRequestAction,
    @SerialName("number")
    val number: Int,
) : RemoteUserEventPayload()

enum class RemotePullRequestAction {
    @SerialName("assigned")
    Assigned,

    @SerialName("unassigned")
    Unassigned,

    @SerialName("review_requested")
    ReviewRequested,

    @SerialName("review_request_removed")
    ReviewRequestRemoved,

    @SerialName("labeled")
    Labeled,

    @SerialName("unlabeled")
    Unlabeled,

    @SerialName("opened")
    Opened,

    @SerialName("edited")
    Edited,

    @SerialName("closed")
    Closed,

    @SerialName("reopened")
    Reopened,

    @SerialName("synchronize")
    Synchronize,
}
