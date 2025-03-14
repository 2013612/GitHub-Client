package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssuesEventPayload(
    val action: RemoteIssueAction,
    val issue: RemoteIssue,
) : RemoteUserEventPayload()

enum class RemoteIssueAction {
    Opened,
    Edited,
    Closed,
    Reopened,
    Assigned,
    Unassigned,
    Labeled,
    Unlabeled,
}
