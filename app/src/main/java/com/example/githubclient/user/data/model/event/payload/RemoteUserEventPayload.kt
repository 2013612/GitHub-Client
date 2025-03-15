package com.example.githubclient.user.data.model.event.payload

import kotlinx.serialization.Serializable

sealed interface RemoteUserEventPayload

@Serializable
data object RemoteWatchEventPayload : RemoteUserEventPayload

@Serializable
data object RemoteSponsorshipEventPayload : RemoteUserEventPayload

@Serializable
data object RemoteReleaseEventPayload : RemoteUserEventPayload

@Serializable
data object RemotePullRequestReviewEventPayload : RemoteUserEventPayload

@Serializable
data object RemotePullRequestReviewCommentEventPayload : RemoteUserEventPayload

@Serializable
data object RemotePublicEventPayload : RemoteUserEventPayload

@Serializable
data object RemoteUnknownEventPayload : RemoteUserEventPayload
