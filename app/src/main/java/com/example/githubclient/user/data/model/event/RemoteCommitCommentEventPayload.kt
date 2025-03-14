package com.example.githubclient.user.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCommitCommentEventPayload(
    val action: RemoteCommentAction,
    val comment: RemoteCommitComment,
) : RemoteUserEventPayload()

@Serializable
data class RemoteCommitComment(
    val htmlUrl: String,
    val id: Long,
    val commitId: String,
)
