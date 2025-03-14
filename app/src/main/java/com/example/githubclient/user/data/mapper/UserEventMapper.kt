package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.CommitCommentEventPayload
import com.example.githubclient.user.data.model.RemotePublicUserEvent
import com.example.githubclient.user.domain.model.event.CommitCommentEvent
import com.example.githubclient.user.domain.model.event.UserEvent

fun RemotePublicUserEvent.toUserEvent(): UserEvent? =
    when (payload) {
        is CommitCommentEventPayload ->
            CommitCommentEvent(
                id = id,
                time = createdAt,
                commitId = payload.comment.commitId,
                repoName = repo.name,
            )
        else -> null
    }
