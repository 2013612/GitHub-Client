package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssueCommentEventPayload(
    val action: RemoteCommentAction,
    val issue: RemoteIssue,
    val comment: RemoteComment,
) : RemoteUserEventPayload()
