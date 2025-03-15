package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssuesEventPayload(
    val action: RemoteIssueAction,
    val issue: RemoteIssue,
) : RemoteUserEventPayload()

enum class RemoteIssueAction {
    @SerialName("opened")
    Opened,

    @SerialName("edited")
    Edited,

    @SerialName("closed")
    Closed,

    @SerialName("reopened")
    Reopened,

    @SerialName("assigned")
    Assigned,

    @SerialName("unassigned")
    Unassigned,

    @SerialName("labeled")
    Labeled,

    @SerialName("unlabeled")
    Unlabeled,
}
