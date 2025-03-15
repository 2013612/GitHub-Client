package com.example.githubclient.user.data.model.event

import com.example.githubclient.user.data.model.event.payload.RemoteUserEventPayload
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = RemotePublicUserEventSerializer::class)
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
    PullRequestReviewThreadEvent,
    PushEvent,
    ReleaseEvent,
    SponsorshipEvent,
    WatchEvent,
    Unknown,
}

@Serializable
enum class RemoteGitRefType {
    @SerialName("branch")
    Branch,

    @SerialName("tag")
    Tag,

    @SerialName("repository")
    Repository,
}

@Serializable
enum class RemoteCommentAction {
    @SerialName("created")
    Created,

    @SerialName("edited")
    Edited,

    @SerialName("deleted")
    Deleted,
}
