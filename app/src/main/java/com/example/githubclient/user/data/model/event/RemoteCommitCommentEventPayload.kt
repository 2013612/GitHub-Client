package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCommitCommentEventPayload(
    val action: RemoteCommentAction,
    val comment: RemoteCommitComment,
) : RemoteUserEventPayload()

enum class RemoteCommentAction {
    @SerialName("created")
    Created,

    @SerialName("edited")
    Edited,

    @SerialName("deleted")
    Deleted,
}

@Serializable
data class RemoteCommitComment(
    val htmlUrl: String,
    val id: Long,
    val commitId: String,
)
