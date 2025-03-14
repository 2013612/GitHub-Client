package com.example.githubclient.user.data.model.event

import com.example.githubclient.user.data.model.Issue
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssueCommentEventPayload(
    val action: RemoteCommentAction,
    val issue: Issue,
    val comment: RemoteComment,
) : RemoteUserEventPayload()
