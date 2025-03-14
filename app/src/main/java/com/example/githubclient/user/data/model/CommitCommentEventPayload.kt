package com.example.githubclient.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitCommentEventPayload(
    val action: CommentAction,
    val comment: CommitComment,
) : Payload()

enum class CommentAction {
    @SerialName("created")
    Created,

    @SerialName("edited")
    Edited,

    @SerialName("deleted")
    Deleted,
}

@Serializable
data class CommitComment(
    val htmlUrl: String,
    val id: Long,
    val commitId: String,
)
