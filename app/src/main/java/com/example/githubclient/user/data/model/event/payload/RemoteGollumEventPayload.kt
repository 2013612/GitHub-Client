package com.example.githubclient.user.data.model.event.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGollumEventPayload(
    val pages: List<RemoteWikiPage>,
) : RemoteUserEventPayload

@Serializable
data class RemoteWikiPage(
    @SerialName("page_name")
    val pageName: String,
    val action: RemoteWikiAction,
    @SerialName("html_url")
    val htmlUrl: String,
)

@Serializable
enum class RemoteWikiAction {
    @SerialName("created")
    Created,

    @SerialName("edited")
    Edited,
}
