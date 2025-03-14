package com.example.githubclient.user.data.model.event

import kotlinx.serialization.SerialName

data class RemoteGollumEventPayload(
    val pages: List<RemoteWikiPage>,
) : RemoteUserEventPayload()

data class RemoteWikiPage(
    @SerialName("page_name")
    val pageName: String,
    val action: RemoteWikiAction,
    @SerialName("html_url")
    val htmlUrl: String,
)

enum class RemoteWikiAction {
    @SerialName("created")
    Created,

    @SerialName("edited")
    Edited,
}
