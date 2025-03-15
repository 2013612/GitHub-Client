package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCommitCommentEventPayload(
    val action: RemoteCommentAction,
    val comment: RemoteCommitComment,
) : RemoteUserEventPayload()

@Serializable
data class RemoteCommitComment(
    @SerialName("html_url")
    val htmlUrl: String,
    val id: Long,
    @SerialName("commit_id")
    val commitId: String,
)
