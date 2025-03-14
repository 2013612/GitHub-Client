package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePublicUserEvent(
    val id: String,
    val type: RemoteUserEventType,
    val repo: RemoteRepository,
    val payload: RemoteUserEventPayload,
    @SerialName("created_at")
    val createdAt: String,
)

@Serializable
data class RemoteActor(
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("display_login")
    val displayLogin: String? = null,
)

@Serializable
data class RemoteRepository(
    val id: Long,
    val name: String,
    val url: String,
)

@Serializable
sealed class RemoteUserEventPayload

enum class RemoteUserEventType {
    CommitCommentEvent,
    CreateEvent,
    DeleteEvent,
    ForkEvent,
    GollumEvent,
    IssueCommentEvent,
    IssuesEvent,
    MemberEvent,
    PublicEvent,
    PullRequestEvent,
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,
    PushEvent,
    ReleaseEvent,
    WatchEvent,
    Unknown,
}

enum class RemoteGitRefType {
    @SerialName("branch")
    Branch,

    @SerialName("tag")
    Tag,

    @SerialName("repository")
    Repository,
}
