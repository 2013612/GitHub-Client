package com.example.githubclient.user.data.model.event.payload

import com.example.githubclient.user.data.model.event.RemoteCommentAction
import com.example.githubclient.user.data.model.event.payload.RemoteComment
import com.example.githubclient.user.data.model.event.payload.RemoteIssue
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIssueCommentEventPayload(
    val action: RemoteCommentAction,
    val issue: RemoteIssue,
    val comment: RemoteComment,
) : RemoteUserEventPayload
