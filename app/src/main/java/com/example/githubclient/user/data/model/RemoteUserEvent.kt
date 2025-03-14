package com.example.githubclient.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePublicUserEvent(
    val id: String,
    val type: EventType,
    val repo: Repository,
    val payload: Payload,
    @SerialName("created_at")
    val createdAt: String,
)

@Serializable
data class Actor(
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("display_login")
    val displayLogin: String? = null,
)

@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val url: String,
)

@Serializable
sealed class Payload

enum class EventType {
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
